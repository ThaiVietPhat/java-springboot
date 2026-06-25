package phat.springframework.springaideep.controllers;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class ChatControllers {

    private final ChatClient chatClient;

    public ChatControllers(ChatClient.Builder builder) {
        this.chatClient = builder
                .defaultSystem("Bạn là giáo viên, luôn giải thích ngắn gọn, dễ hiểu")
                .build();
    }

    // Endpoint 1: không dùng template - user truyền thẳng string
    @PostMapping("/chat")
    public String chat(@RequestBody String message) {
        return chatClient.prompt()
                .user(message)
                .call().content();
    }

    // Endpoint 2: dùng Prompt Template - template có placeholder {topic} và {level}
    // user chỉ truyền biến, AI nhận được câu hoàn chỉnh
    @GetMapping("/chat/template")
    public String chatTemplate(@RequestParam String topic,
                               @RequestParam String level) {
        return chatClient.prompt()
                .user(u -> u.text("Giải thích {topic} cho người học ở mức độ {level}")
                        .params(Map.of(
                                "topic", topic,
                                "level", level
                        )))
                .call().content();
    }
}
