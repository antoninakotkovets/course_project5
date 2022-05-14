package com.ajuncodes.coursework.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class IdField {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public IdField() {}

    public IdField(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
