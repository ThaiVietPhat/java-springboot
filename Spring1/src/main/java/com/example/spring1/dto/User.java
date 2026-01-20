package com.example.spring1.dto;

public class User {
    private int id;
    private String name;
    private int old;

    public User() {
    }

    public User(int id, String name, int old) {
        this.id = id;
        this.name = name;
        this.old = old;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOld() {
        return old;
    }

    public void setOld(int old) {
        this.old = old;
    }
}
