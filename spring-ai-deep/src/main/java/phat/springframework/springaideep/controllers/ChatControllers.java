package phat.springframework.springaideep.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ChatControllers {

    private final ChatClient chatClient;

    @PostMapping("/chatjava")
    public String chatJava(@RequestBody String message) {
        return chatClient.prompt()
                .system("Tập trung vào java, spring")
                .user(message)
                .call()
                .content();
    }

    @PostMapping("/chatC#")
    public String chatC(@RequestBody String message) {
        return chatClient.prompt()
                .system("Tập trung vào c#, .NET")
                .user(message)
                .call()
                .content();
    }
}
