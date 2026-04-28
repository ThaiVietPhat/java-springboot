package phat.springframework.springai.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import phat.springframework.springai.model.Answer;
import phat.springframework.springai.model.GetCapitalRequest;
import phat.springframework.springai.model.Question;

import java.io.IOException;
import java.util.Map;

@Service
public class OpenAIServiceImpl implements OpenAIService{


    @Value("classpath:templates/get-capital-prompt.st")
    private Resource capitalPromptTemplate;
    @Value("classpath:templates/get-capital-with-info.st")
    private Resource getCapitalPromptWithInfo;
    @Value("classpath:templates/get-capital-json.st")  // Thêm template mới cho JSON
    private Resource getCapitalJsonTemplate;
    private final ChatClient chatClient;

    public OpenAIServiceImpl(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }
    @Override
    public Answer getCapitalWithInfo(GetCapitalRequest getCapitalRequest) {
        String question = loadAndFormatTemplate(getCapitalPromptWithInfo,
                Map.of("stateOrCountry", getCapitalRequest.stateOrCountry()));

        String capitalAnswer = chatClient
                .prompt()
                .user(question)
                .call()
                .content();

        return new Answer(capitalAnswer);
    }

    private String loadTemplate(Resource template) {
        try {
            return new String(template.getInputStream().readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException("Failed to load template: " + template.getFilename(), e);
        }
    }
    @Override
    public Answer getCapital(GetCapitalRequest getCapitalRequest) {
        //String question = "What is the capital of " + getCapitalRequest.stateOrCountry() + "?";
        String question = loadAndFormatTemplate(capitalPromptTemplate,
                Map.of("stateOrCountry", getCapitalRequest.stateOrCountry()));

        String capitalAnswer = chatClient
                .prompt()
                .user(question)
                .call()
                .content();

        return new Answer(capitalAnswer);
    }
    private String loadAndFormatTemplate(Resource template, Map<String, String> params) {
        try {
            String content = new String(template.getInputStream().readAllBytes());
            for (Map.Entry<String, String> entry : params.entrySet()) {
                content = content.replace("{" + entry.getKey() + "}", entry.getValue());
            }
            return content;
        } catch (IOException e) {
            throw new RuntimeException("Failed to load template", e);
        }
    }

    @Override
    public Answer getAnswer(Question question) {
        return chatClient
                .prompt()
                .user(question.question())
                .call()
                .entity(Answer.class);
    }


    @Override
    public String getAnswer(String question) {
        return chatClient
                .prompt()
                .user(question)
                .call()
                .content();
    }
}

