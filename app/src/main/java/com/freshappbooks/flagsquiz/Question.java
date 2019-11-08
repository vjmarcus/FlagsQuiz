package com.freshappbooks.flagsquiz;

public class Question {

    private int id;
    private String questionText;
    private String imagePath;

    public Question(int id, String questionText) {
        this.id = id;
        this.questionText = questionText;
    }

    public Question(int id, String questionText, String imagePath) {
        this.id = id;
        this.questionText = questionText;
        this.imagePath = imagePath;
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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
