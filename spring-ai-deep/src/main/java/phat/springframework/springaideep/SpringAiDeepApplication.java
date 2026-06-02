package phat.springframework.springaideep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.ai.chat.client.ChatClient;

@SpringBootApplication
public class SpringAiDeepApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAiDeepApplication.class, args);
    }

    @Bean
    public ChatClient chatClient(ChatClient.Builder builder) {
        return builder.build();
    }
}
