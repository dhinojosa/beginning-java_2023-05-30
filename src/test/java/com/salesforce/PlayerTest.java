package com.salesforce;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    @Test
    void testThatAPlayerIsAPerson() {
        Player player = new Player("A", "B", "C",
            LocalDate.of(1995, 3, 10),
            () -> LocalDate.now(),
            LocalDate.of(2022, 1, 1),
            "University of Minnesota",
            "Center");
        Person person = player;
        Object object = person;

    }
}
