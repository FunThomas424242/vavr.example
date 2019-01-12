package com.github.funthomas424242.examples.vavr;

import io.vavr.collection.Seq;
import io.vavr.control.Validation;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class PersonValidator {

    String NAME_ERR = "Ungültige Zeichen: ";
    String AGE_ERR = "Alter muss größer 0 sein.";

    public Validation<Seq<String>, Person> validatePerson(
            String vorname, String surname, LocalDate birthday) {
        final Person person = new Person(vorname,surname,birthday);
        return Validation.combine(
                validateName(vorname)
                , validateName(surname)
                , validateAge(birthday)).ap(Person::new);
    }

    private Validation<String, String> validateName(String name) {
        String invalidChars = name.replaceAll("[a-zA-Z ]", "");
        return invalidChars.isEmpty() ?
                Validation.valid(name)
                : Validation.invalid(NAME_ERR + invalidChars);
    }

    /**
     * Alter muss größer 0 sein
     *
     * @param birthday
     * @return
     */
    private Validation<String, LocalDate> validateAge(LocalDate birthday) {

        return birthday.isBefore(LocalDate.now()) ? Validation.valid(birthday)
                : Validation.invalid(AGE_ERR);
    }

}
