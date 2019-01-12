package com.github.funthomas424242.examples.vavr;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class Person {

    protected String vorname;

    protected String surname;


    protected LocalDate birthday;

//    public Person() {
//
//    }

    public Person(final String vorname, final String surname, final LocalDate birthday) {
        this.vorname = vorname;
        this.surname = surname;
        this.birthday = birthday;
    }

//    public void setBirthday(LocalDate birthday) {
//        this.birthday = birthday;
//    }
//
//    public void setVorname(String vorname) {
//        this.vorname = vorname;
//    }
//
//    public void setSurname(String surname) {
//        this.surname = surname;
//    }

    public String getVorname() {
        return vorname;
    }

    public String getSurname() {
        return surname;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    @Override
    public String toString() {
        return "Person{" +
                "vorname='" + vorname + '\'' +
                ", surname='" + surname + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
