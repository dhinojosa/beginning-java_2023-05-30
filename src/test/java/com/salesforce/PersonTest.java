package com.salesforce;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {
    @Test
    void testSomeoneWithNoMiddleName() {
        Person person = Person.withNoMiddleName("Daniel", "Hinojosa",
            LocalDate.of(1972, 5, 1));
        Optional<String> middleName = person.getMiddleName();
        String middleInitial = middleName
            .map(mn -> mn.charAt(0) + ".")
            .orElse("");
        System.out.println(middleInitial);
    }

    @Test
    void testSomeoneWithAMiddleName() {
        Person person = Person.withMiddleName("Prince", "Rogers", "Nelson",
            LocalDate.of(1958, 6, 7));
        Optional<String> middleName = person.getMiddleName();
        String middleInitial = middleName
            .map(mn -> mn.charAt(0) + ".")
            .orElse("");

        System.out.println(middleInitial);
    }

    @Test
    void testOptionalMonad() {
        Optional<Integer> o1 = Optional.of(10);
        Optional<Integer> o2 = Optional.empty();
        Optional<Integer> o3 = Optional.of(30);

        Optional<Integer> integer =
            o1.flatMap(x -> o2.flatMap(y -> o3.map(z -> x + y + z)));
        System.out.println(integer);
    }

    @Test
    void testSomeonesAge() {
        Person person = Person.withNoMiddleName("Daniel", "Hinojosa",
            LocalDate.of(1972, 5, 1));
        int age = person.getAge();
        System.out.println(age);
    }
}
