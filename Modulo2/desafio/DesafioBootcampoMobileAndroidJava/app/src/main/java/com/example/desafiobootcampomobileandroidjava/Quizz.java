package com.example.desafiobootcampomobileandroidjava;

public class Quizz {


    public Quizz(String question, boolean answer) {
        this.question = question;
        this.answer = answer;
    }

    String question;
    Boolean answer;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Boolean getAnswer() {
        return answer;
    }

    public void setAnswer(Boolean answer) {
        this.answer = answer;
    }
}
