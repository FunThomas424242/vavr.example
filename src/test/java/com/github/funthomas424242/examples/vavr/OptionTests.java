package com.github.funthomas424242.examples.vavr;

import io.vavr.control.Option;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Vavr Option dient der Vermeidung von Nullpointer und Exceptionhandling.
 * Die Vavr Option verfügt über mehr Features aus Guava Optional.
 */
public class OptionTests {



    @Test
    @DisplayName("Option handle NPE with NoneDefault")
    public void option_toHandleNPE_withNoneDefault() {

        final Option<String> messageText = Option.of(null);

        assertEquals("None", messageText.toString());
    }


    @Test
    @DisplayName("Option handle NPE with UserdefinedDefault")
    public void option_toHandleNPE_withUserDefinedDefault() {

        final Option<String> messageText = Option.of(null);

        assertEquals("DefaultValue", messageText.getOrElse("DefaultValue"));
    }

    @Test
    @DisplayName("Option handle NPE with NoSuchElementException")
    public void option_toHandleNPE_withNoSucheElementException() {

        final Option<String> messageText = Option.of(null);

        Assertions.assertThrows(NoSuchElementException.class, () -> {
            messageText.get();
        });
    }



}
