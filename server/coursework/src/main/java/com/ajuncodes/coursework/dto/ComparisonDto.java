package com.ajuncodes.coursework.dto;


import com.ajuncodes.coursework.model.Language;

public class ComparisonDto {

    private String firstText;
    private String secondText;

    private Language language;

    public String getFirstText() {
        return firstText;
    }

    public void setFirstText(String firstText) {
        this.firstText = firstText;
    }

    public String getSecondText() {
        return secondText;
    }

    public void setSecondText(String secondText) {
        this.secondText = secondText;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }
}
