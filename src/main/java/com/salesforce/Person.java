package com.salesforce;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.function.Supplier;

public class Person {
    private String firstName;
    private String lastName;

    private String middleName;

    private LocalDate birthDay;
    private final Supplier<LocalDate> todaysDate;


    protected Person(String firstName, String middleName,
                     String lastName, LocalDate birthDay,
                     Supplier<LocalDate> todaysDate) {

        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.todaysDate = todaysDate;
    }

    public static Person withNoMiddleName(String firstName,
                                          String lastName,
                                          LocalDate birthDay) {
        return new Person(firstName, null, lastName, birthDay, LocalDate::now);
    }

    public static Person withMiddleName(String firstName, String middleName, String lastName
        , LocalDate birthDay) {
        return new Person(firstName, middleName, lastName, birthDay, LocalDate::now);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Optional<String> getMiddleName() {
        return Optional.ofNullable(middleName);
    }

    public int getAge() {
        return (int) ChronoUnit.YEARS.between(birthDay, todaysDate.get());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Person.class.getSimpleName() + "[", "]")
            .add("firstName='" + firstName + "'")
            .add("lastName='" + lastName + "'")
            .toString();
    }
}
