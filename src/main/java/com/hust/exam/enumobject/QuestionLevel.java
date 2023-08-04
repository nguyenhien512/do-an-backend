package com.hust.exam.enumobject;

public enum QuestionLevel {
    LEVEL_1("Nhận biết"), LEVEL_2("Thông hiểu"), LEVEL_3("Vận dụng"), LEVEL_4("Vận dụng cao");

    private String text;

    QuestionLevel(String text) {
        this.text = text;
    }
    public String getText() {
        return text;
    }
}
