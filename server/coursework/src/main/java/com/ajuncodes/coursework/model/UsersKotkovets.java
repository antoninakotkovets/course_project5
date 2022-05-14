package com.ajuncodes.coursework.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class UsersKotkovets extends IdField {
    private String login;
    private String password;
    private String role;
    private String secretCode;

    @OneToMany()
    List<ComparisonResultKotkovets> comparisonResult;

    @OneToMany()
    List<PesonalInfKotkovets> personalInfo;

    public UsersKotkovets() { }

    public UsersKotkovets(UsersKotkovets usersKotkovets) {
        this.setId(usersKotkovets.getId());
        this.login = usersKotkovets.getLogin();
        this.password = usersKotkovets.getPassword();
        this.role = usersKotkovets.getRole();
        this.secretCode = usersKotkovets.getSecretCode();
    }

    public UsersKotkovets(String login, String password, String role, String secretCode) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.secretCode = secretCode;
    }
}
