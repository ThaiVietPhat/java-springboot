package phat.springframework.springai.service;

import phat.springframework.springai.model.Answer;
import phat.springframework.springai.model.GetCapitalRequest;
import phat.springframework.springai.model.Question;

public interface OpenAIService {
    String getAnswer(String question);
    Answer getAnswer(Question question);

    Answer getCapital(GetCapitalRequest getCapitalRequest);

    Answer getCapitalWithInfo(GetCapitalRequest getCapitalRequest);
}
