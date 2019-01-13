package com.github.funthomas424242.examples.vavr;

import jdk.nashorn.internal.AssertsEnabled;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.vavr.API.*;
import static io.vavr.Predicates.isIn;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Mit dem Matcher Konzept aus dem funktionalem Paradigma lassen sich Fallunterscheidungen verkürzen.")
public class MatcherTest {

    @Test
    @DisplayName("Verkürzung Switch case statement")
    public void whenMatchworks_thenCorrect() {
        int input = 2;
        String output = Match(input).of(
                Case($(1), "one"),
                Case($(2), "two"),
                Case($(3), "three"),
                Case($(), "?"));

        assertEquals("two", output);
    }

    @Test
    @DisplayName("Ausführung abhängig vom Commandline Argument")
    public void pruefeCommandlineParameters() {
        String arg = "-v";
        final Object result = Match(arg).of(
                Case($(isIn("-h", "--help")), o -> run(this::displayHelp)),
                Case($(isIn("-v", "--version")), o -> run(this::displayVersion)),
                Case($(), o -> run(() -> {
                    throw new IllegalArgumentException(arg);
                }))
        );
        Assertions.assertNull(result);
    }

    private void displayVersion() {
        Assertions.assertTrue(true);
        System.out.println("--version war gemeint");
    }

    private void displayHelp() {
        System.out.println("--help war gemeint.");
        Assertions.fail();
    }
}
