package phat.springframework.springaideep.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/template")
public class PromptTemplateController {

    private final ChatClient chatClient;

    // Spring tự load file explain.st từ classpath/resources/templates/
    @Value("classpath:templates/explain.st")
    private Resource explainTemplate;

    @PostMapping("/explain")
    public String explain(@RequestBody Map<String, String> body) {
        // Đọc template từ file .st thay vì hardcode trong code
        PromptTemplate promptTemplate = new PromptTemplate(explainTemplate);

        Prompt prompt = promptTemplate.create(Map.of(
                "topic", body.get("topic"),
                "language", body.get("language")
        ));

        return chatClient.prompt(prompt)
                .call()
                .content();
    }
}
