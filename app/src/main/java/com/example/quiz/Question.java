package com.example.quiz;

public class Question {
    String question;
    String optionA;
    String optionB;
    String optionC;
    String correctAnswer;

    public Question(String question, String optionA, String optionB, String optionC, String correctAnswer) {
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.correctAnswer = correctAnswer;
    }
}