package net.dmitrykornilov.helidon.assistant;

import io.helidon.http.Header;
import io.helidon.http.HeaderNames;
import io.helidon.http.HeaderValues;

import io.helidon.common.config.Config;
import io.helidon.http.Status;
import io.helidon.logging.common.LogConfig;
import io.helidon.service.registry.Services;
import io.helidon.webserver.WebServer;
import io.helidon.webserver.http.HttpRouting;
import io.helidon.webserver.staticcontent.StaticContentService;

import net.dmitrykornilov.helidon.assistant.rag.DocsIngestor;
import net.dmitrykornilov.helidon.assistant.rest.ChatBotService;

public class ApplicationMain {

    private static final Header UI_REDIRECT = HeaderValues.createCached(HeaderNames.LOCATION, "/ui");

    public static void main(String[] args) {
        // Make sure logging is enabled as the first thing
        LogConfig.configureRuntime();

        var config = Services.get(Config.class);

        // Initialize embedding store
        Services.get(DocsIngestor.class)
                .ingest();

        WebServer.builder()
                .config(config.get("server"))
                .routing(ApplicationMain::routing)
                .build()
                .start();
    }

    /**
     * Updates HTTP Routing.
     */
    static void routing(HttpRouting.Builder routing) {
        routing.any("/", (req, res) -> {
                    // showing the capability to run on any path, and redirecting from root
                    res.status(Status.MOVED_PERMANENTLY_301);
                    res.headers().set(UI_REDIRECT);
                    res.send();
                })
                .register("/chat", Services.get(ChatBotService.class))
                .register("/ui", StaticContentService.builder("WEB")
                        .welcomeFileName("index.html")
                        .build());
    }
}
