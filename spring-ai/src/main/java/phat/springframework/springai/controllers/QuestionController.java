package phat.springframework.springai.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import phat.springframework.springai.model.Answer;
import phat.springframework.springai.model.GetCapitalRequest;
import phat.springframework.springai.model.Question;
import phat.springframework.springai.service.OpenAIService;
@RestController
@RequiredArgsConstructor
public class QuestionController {
    private final OpenAIService openAIService;
    @PostMapping("/ask")
    public Answer askQuestion(@RequestBody Question question){
        return openAIService.getAnswer(question);
    }
    @PostMapping("/capital")
    public Answer getCapital(@RequestBody GetCapitalRequest getCapitalRequest){
        return this.openAIService.getCapital(getCapitalRequest);
    }
    @PostMapping("/capitalWithInfo")
    public Answer getCapitalWithInfo(@RequestBody GetCapitalRequest getCapitalRequest){
        return this.openAIService.getCapitalWithInfo(getCapitalRequest);
    }

}
