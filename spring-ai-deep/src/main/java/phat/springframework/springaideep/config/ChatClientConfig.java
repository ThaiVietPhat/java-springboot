package phat.springframework.springaideep.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import phat.springframework.springaideep.advisor.TokenUsageAuditAdvisor;

@Configuration
public class ChatClientConfig {

    @Bean
    public ChatClient chatClient(ChatClient.Builder builder) {
        return builder
                .defaultSystem("Là 1 giáo viên giỏi, đang giải đáp thắc mắc")
                .defaultUser("Giải thích dễ hiểu. Đi vào lý thuyet va vi du")
                .defaultAdvisors(new TokenUsageAuditAdvisor())
                .defaultOptions(OpenAiChatOptions.builder()
                        .model("deepseek-chat")
                        .temperature(0.7)
                        .maxTokens(1024))
                .build();
    }
}
