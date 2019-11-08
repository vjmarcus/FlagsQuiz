package com.freshappbooks.flagsquiz;

public class Question {

    private int id;
    private String questionText;
    private String rightAnswer;

    public Question(int id, String questionText, String rightAnswer) {
        this.id = id;
        this.questionText = questionText;
        this.rightAnswer = rightAnswer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }
}
