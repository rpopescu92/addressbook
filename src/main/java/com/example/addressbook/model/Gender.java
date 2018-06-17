package com.example.addressbook.model;

public enum Gender {

    MALE,
    FEMALE;

    public static Gender stringToEnum(String gender) {
        return Gender.valueOf(gender.toUpperCase());
    }
}
