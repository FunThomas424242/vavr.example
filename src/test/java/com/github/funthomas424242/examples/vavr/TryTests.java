package com.github.funthomas424242.examples.vavr;

import io.vavr.control.Try;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.Supplier;

public class TryTests {

    @Test
    public void divisionDurchNull_WithException(){
        Try<Integer> result = Try.of(() -> {
           return 11/0;
        });
        Assertions.assertTrue(result.isFailure());
    }

    @Test
    public void divisionDurchNull_WithDefault(){
        Try<Integer> result = Try.of(() -> {
            return 11/0;
        });
        Assertions.assertEquals((Integer)5, result.getOrElse(5));
    }

    @Test
    public void divisionDurchNull_throwException(){
        Try<Integer> result = Try.of(() -> {
            return 11/0;
        });
        Assertions.assertThrows(ArithmeticException.class, () -> {
            result.getOrElseThrow((Supplier<ArithmeticException>) ArithmeticException::new);
        });
    }

}
