package phat.springframework.springaideep.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class ChatControllers {

    private final ChatClient chatClient;

    public record ChatRequest(String message) {}

    @PostMapping("chat")
    public String chat(@RequestBody ChatRequest request) {
        return chatClient.prompt(request.message()).call().content();
    }
}
