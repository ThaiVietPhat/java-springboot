package phat.springframework.springaideep.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/stuffing")
public class PromptStuffingController {

    private final ChatClient chatClient;

    @Value("classpath:templates/stuffing.st")
    private Resource stuffingTemplate;

    // Tài liệu nội bộ được "nhét" vào prompt
    @Value("classpath:docs/java-policy.txt")
    private Resource document;

    @PostMapping("/ask")
    public String ask(@RequestBody Map<String, String> body) throws IOException {
        // Đọc nội dung tài liệu thành String
        String documentContent = document.getContentAsString(StandardCharsets.UTF_8);

        PromptTemplate promptTemplate = new PromptTemplate(stuffingTemplate);

        // Nhét tài liệu + câu hỏi vào template
        Prompt prompt = promptTemplate.create(Map.of(
                "document", documentContent,
                "question", body.get("question")
        ));

        return chatClient.prompt(prompt)
                .call()
                .content();
    }
}
