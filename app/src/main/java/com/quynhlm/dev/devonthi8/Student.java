package com.quynhlm.dev.devonthi8;

public class Student {
    private int id;
    private String name;
    private int birthday;
    private int MSSV;

    public Student() {
    }

    public Student(int id, String name, int birthday, int MSSV) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.MSSV = MSSV;
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

    public int getBirthday() {
        return birthday;
    }

    public void setBirthday(int birthday) {
        this.birthday = birthday;
    }

    public int getMSSV() {
        return MSSV;
    }

    public void setMSSV(int MSSV) {
        this.MSSV = MSSV;
    }
}
