package com.ajuncodes.coursework.model;

//фамилия, имя, отчество, пол, дата рождения


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class PesonalInfKotkovets extends IdField {
    private String surname;
    private String name;
    private String lastName;
    private String gender;
    private Date date;

    @ManyToOne()
    UsersKotkovets user;

    public PesonalInfKotkovets() { }

    public PesonalInfKotkovets(PesonalInfKotkovets pesonalInfKotkovets) {
        this.setId(pesonalInfKotkovets.getId());
        this.surname = pesonalInfKotkovets.getSurname();
        this.name = pesonalInfKotkovets.getName();
        this.lastName = pesonalInfKotkovets.getLastName();
        this.gender = pesonalInfKotkovets.getGender();
        this.date = pesonalInfKotkovets.getDate();
        this.user = pesonalInfKotkovets.getUser();
    }

    public PesonalInfKotkovets(String surname, String name, String lastName, String gender, Date date, UsersKotkovets user) {
        this.surname = surname;
        this.name = name;
        this.lastName = lastName;
        this.gender = gender;
        this.date = date;
        this.user = user;
    }
}



