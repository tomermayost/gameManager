package com.example.gameManager.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class Question {

    private String questionBody;
    @JsonIgnore
    private List<String> possibleAnswers;

    public String getQuestionBody() {
        return questionBody;
    }

    public void setQuestionBody(String questionBody) {
        this.questionBody = questionBody;
    }

    public List<String> getPossibleAnswers() {
        return possibleAnswers;
    }

    public void setPossibleAnswers(List<String> possibleAnswers) {
        this.possibleAnswers = possibleAnswers;
    }
}
