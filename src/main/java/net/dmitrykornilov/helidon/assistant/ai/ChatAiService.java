package net.dmitrykornilov.helidon.assistant.ai;

import io.helidon.integrations.langchain4j.Ai;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

@Ai.Service
public interface ChatAiService {

    @SystemMessage("""
            You are Frank, a helpful Helidon expert.
            
            Only answer questions related to Helidon and its components. If a question is not relevant to Helidon, 
            politely decline.
            
            Use the following conversation summary to keep context and maintain continuity:
            {(summary})
            """)
    String chat(@UserMessage String question, @V("summary") String previousConversationSummary);
}