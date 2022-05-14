package com.ajuncodes.coursework.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class VisitStatisticsKotkovets extends IdField {
    private String login;
    private String timeVisit;

    @ManyToOne()
    UsersKotkovets user;

    public VisitStatisticsKotkovets(){ }

    public VisitStatisticsKotkovets(VisitStatisticsKotkovets visitStatisticsKotkovets) {
        this.setId(visitStatisticsKotkovets.getId());
        this.login = visitStatisticsKotkovets.getLogin();
        this.timeVisit = visitStatisticsKotkovets.getTimeVisit();
        this.user = visitStatisticsKotkovets.getUser();
    }

    public VisitStatisticsKotkovets(String login, String timeVisit, UsersKotkovets user) {
        this.login = login;
        this.timeVisit = timeVisit;
        this.user = user;
    }
}
