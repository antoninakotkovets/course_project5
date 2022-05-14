package com.ajuncodes.coursework.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class DataСomparisonKotkovets extends IdField {
    private long lengthFirstText;
    private long lengthSecondText;
    private String language;

    @ManyToOne()
    ComparisonResultKotkovets comparison;

    public DataСomparisonKotkovets(){ }

    public DataСomparisonKotkovets(DataСomparisonKotkovets dataСomparisonKotkovets) {
        this.setId(dataСomparisonKotkovets.getId());
        this.lengthFirstText = dataСomparisonKotkovets.getLengthFirstText();
        this.lengthSecondText = dataСomparisonKotkovets.getLengthSecondText();
        this.language = dataСomparisonKotkovets.getLanguage();
        this.comparison = dataСomparisonKotkovets.getComparison();
    }

    public DataСomparisonKotkovets(long lengthFirstText, long lengthSecondText, String language, ComparisonResultKotkovets comparison) {
        this.lengthFirstText = lengthFirstText;
        this.lengthSecondText = lengthSecondText;
        this.language = language;
        this.comparison = comparison;
    }
}
