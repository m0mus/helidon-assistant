package net.dmitrykornilov.helidon.assistant.ai;

import io.helidon.integrations.langchain4j.Ai;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;

@Ai.Service
@Ai.ChatMemoryWindow(value=3,store="ChatMemoryStore")
public interface ChatAiService {

    @SystemMessage("""
            You are Frank, a helpful Helidon expert.
            
            Only answer questions related to Helidon and its components. If a question is not relevant to Helidon, 
            politely decline.
            """)
    String chat(@UserMessage String question);
}