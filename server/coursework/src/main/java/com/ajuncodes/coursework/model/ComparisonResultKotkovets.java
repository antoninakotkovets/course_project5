package com.ajuncodes.coursework.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class ComparisonResultKotkovets extends IdField {
    private String titleСomparison;
    private Double percentComparison;
    private Date timeComparison;

    @ManyToOne()
    UsersKotkovets user;

    @OneToMany()
    List<DataСomparisonKotkovets> dataСomparison;

    public ComparisonResultKotkovets(){ }

    public ComparisonResultKotkovets (ComparisonResultKotkovets comparisonResultKotkovets) {
        this.setId(comparisonResultKotkovets.getId());
        this.titleСomparison = comparisonResultKotkovets.getTitleСomparison();
        this.timeComparison = comparisonResultKotkovets.getTimeComparison();
        this.percentComparison = comparisonResultKotkovets.getPercentComparison();
        this.user = comparisonResultKotkovets.getUser();
    }

    public ComparisonResultKotkovets (String titleСomparison, Double percentComparison, Date timeComparison, UsersKotkovets user) {
        this.titleСomparison = titleСomparison;
        this.percentComparison = percentComparison;
        this.timeComparison = timeComparison;
        this.user = user;
    }
}
