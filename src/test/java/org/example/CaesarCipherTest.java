package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для шифра Цезаря (английский алфавит)
 */
class CaesarCipherTest {

    private CaesarCipher cipher;
    private Alphabet alphabet;

    @BeforeEach
    void setup() {
        alphabet = new Alphabet("EN");
        cipher = new CaesarCipher(alphabet);
    }

    @Test
    @DisplayName("Encrypt simple English text with key=3")
    void testEnglishEncryptSimple() {
        String result = cipher.processText("ABC", 3, true);
        assertEquals("DEF", result);
    }

    @Test
    @DisplayName("Decrypt simple English text with key=3")
    void testEnglishDecryptSimple() {
        String result = cipher.processText("DEF", 3, false);
        assertEquals("ABC", result);
    }

    @Test
    @DisplayName("Wrap-around from Z to C (shift=3)")
    void testEnglishEncryptWrapAround() {
        String result = cipher.processText("XYZ", 3, true);
        assertEquals("ABC", result);
    }

    @Test
    @DisplayName("Validate correct key range (1..25 for EN)")
    void testEnglishValidateKey() {
        assertTrue(cipher.validateKey(5));
        assertTrue(cipher.validateKey(25));
    }

    @Test
    @DisplayName("Reject invalid key (negative, zero, or >=26)")
    void testEnglishInvalidKey() {
        assertFalse(cipher.validateKey(-1));
        assertFalse(cipher.validateKey(0));
        assertFalse(cipher.validateKey(26));
    }

    @Test
    @DisplayName("Accept valid key (1–25 for English)")
    void testEnglishValidKeyRange() {
        assertTrue(cipher.validateKey(1));
        assertTrue(cipher.validateKey(5));
        assertTrue(cipher.validateKey(25));
    }
    @Test
    @DisplayName("Encrypt empty string -> empty string")
    void testEnglishEmptyString() {
        String result = cipher.processText("", 5, true);
        assertEquals("", result);
    }

    @Test
    @DisplayName("Encrypt null should throw NullPointerException")
    void testEnglishNullInput() {
        assertThrows(NullPointerException.class, () -> cipher.processText(null, 5, true));
    }
}
