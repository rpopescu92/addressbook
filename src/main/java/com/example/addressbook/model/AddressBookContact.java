package com.example.addressbook.model;

import java.time.LocalDate;

public class AddressBookContact {

    private final String name;
    private final Gender gender;
    private final LocalDate dob;

    public AddressBookContact(String name, Gender gender, LocalDate dob) {
        this.name = name;
        this.gender = gender;
        this.dob = dob;
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public LocalDate getDob() {
        return dob;
    }

    @Override
    public String toString() {
        return name + ", " + getGender() + ", " + dob;
    }
}
