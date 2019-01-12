package com.github.funthomas424242.examples.vavr;

import io.vavr.collection.Seq;
import io.vavr.control.Validation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidationTest {

    private static PersonValidator validator;

    @BeforeAll
    public static void setUp(){
        validator = new PersonValidator();
    }

    @Test
    public void validiereValidePerson() {

        final Validation<Seq<String>, Person> validPerson = validator.validatePerson(
                "Thomas",
                "Schubert",
                LocalDate.of(1968, 12, 25)
        );

        assertEquals(
                "Valid(Person{vorname='Thomas', surname='Schubert', birthday=1968-12-25})",
                validPerson.toString());

    }

    @Test
    public void validiereInvalidePerson() {

        final Validation<Seq<String>, Person> invalidPerson = validator.validatePerson(
                "Dömas",
                "Schubert",
                LocalDate.now().plus(1,DAYS)
        );

        assertTrue(invalidPerson.isInvalid());
        assertEquals(
                "Invalid(List(Ungültige Zeichen: ö, Alter muss größer 0 sein.))",
                invalidPerson.toString());

    }
}
