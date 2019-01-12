package com.github.funthomas424242.examples.vavr;

import io.vavr.Function0;
import io.vavr.Function2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FunctionalTests {

    @Test
    @DisplayName("Functional mit ohne Parameter")
    public void functionalWithoutParameters() {
        final Function0<String> getClassName = () -> this.getClass().getName();
        Assertions.assertEquals("com.github.funthomas424242.examples.vavr.FunctionalTests",getClassName.apply());
    }

    @Test
    @DisplayName("Functional mit 2 Parametern")
    public void functionalWith2Parameters() {
        final Function2<Long,Long,Long> add = (summand1, summand2 ) -> summand1 + summand2;
        long result = add.apply(2L, 3L);
        Assertions.assertEquals(5, result);
    }

    public double sum(final double summand1, final double summand2){
        return summand1 + summand2;
    }

    @Test
    @DisplayName("Functional erzeugt per Factory Funktion")
    public void functionalWithFactoryFunction() {
        final Function2<Double,Double,Double> sum = Function2.of(this::sum);
        double result = sum.apply(2.3, 3.7);
        Assertions.assertEquals(6, result);
    }


}
