package com.salesforce;

import java.time.LocalDate;
import java.util.function.Supplier;

public class Player extends Person {

    private LocalDate professionalDate;
    private String university;
    private String position;

    protected Player(String firstName,
                     String middleName,
                     String lastName,
                     LocalDate birthDay,
                     Supplier<LocalDate> todaysDate) {
        super(firstName, middleName, lastName, birthDay, todaysDate);
    }

    public Player(String firstName,
                  String middleName,
                  String lastName,
                  LocalDate birthDay,
                  Supplier<LocalDate> todaysDate,
                  LocalDate professionalDate,
                  String university,
                  String position) {
        super(firstName, middleName, lastName, birthDay, todaysDate);
        this.professionalDate = professionalDate;
        this.university = university;
        this.position = position;
    }
}
