package com.example.apprealm.model;

import io.realm.RealmObject;

public class Curso extends RealmObject {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    private String name;
    private String duration;
}