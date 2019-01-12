package com.github.funthomas424242.examples.vavr;

import io.vavr.collection.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CollectionsTests {

    @Test
    public void createValidImmutableList() {

        final List immutableList = List.of(2,3,5,7);
        Assertions.assertEquals(new Integer(2),immutableList.get(0));
        Assertions.assertEquals(new Integer(3),immutableList.get(1));
        Assertions.assertEquals(new Integer(5),immutableList.get(2));
        Assertions.assertEquals(new Integer(7),immutableList.get(3));
        Assertions.assertEquals(4,immutableList.size());

        final int summe = immutableList.sum().intValue();

        Assertions.assertEquals(17,summe);

        final List newList = immutableList.append(11);
        Assertions.assertEquals(4,immutableList.size());
        Assertions.assertEquals(5, newList.size());

    }
}
