package com.github.funthomas424242.examples.vavr;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.Tuple3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

/**
 * Vavr Tupel stellt den Tupel Datentyp in Java zur Verfügung.
 * Tupel werden nur mit bis zu 8 Parametern angeboten.
 */
public class TupleTests {

    @Test
    public void useValidBiTupel() {
        final Tuple2<String, Integer> tupel = Tuple.of("Java", 8);
        Assertions.assertEquals("Java", tupel._1);
        Assertions.assertEquals((Integer) 8, tupel._2());
    }

    @Test
    public void useValidTriTupel() {
        final Tuple3<String, String, LocalDate> tupel = Tuple.of("Thomas", "Schubert", LocalDate.of(1968, 12, 25));

        Assertions.assertEquals("Thomas",tupel._1);
        Assertions.assertEquals("Schubert",tupel._2);
        Assertions.assertEquals(LocalDate.of(1968,12,25),tupel._3);
    }

}
