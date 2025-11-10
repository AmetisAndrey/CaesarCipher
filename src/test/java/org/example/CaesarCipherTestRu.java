package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;


import static org.junit.jupiter.api.Assertions.*;

class CaesarCipherTestRu {

    private CaesarCipher cipher;
    private Alphabet alphabet;

    @BeforeEach
    void setup(){
        alphabet = new Alphabet("RU");
        cipher = new CaesarCipher(alphabet);
    }

    @Test
    @DisplayName("Encrypt simple russian text with key 3")
    void testEncryptSimpleRussian(){
        String result = cipher.processText("АБВ", 3, true);
        assertEquals("ВГД", result);
    }
    @Test
    @DisplayName("Decrypt simple russian text with key 3")
    void testDecryptSimpleRussian(){
        String result = cipher.processText("ВГД", 3, false);
        assertEquals("АБВ", result);
    }
    @Test
    @DisplayName("Wrap-around from Я-А")
    void testEncryptWrapAroundRussian(){
        String result = cipher.processText("ЮЯА", 3, true);
        assertEquals("БВГ", result);
    }
    @Test
    @DisplayName("")
    void testDecryptWrapAroundRussian(){
        String result = cipher.processText("БВГ", 3, false);
        assertEquals("ЮЯА", result);
    }
    @Test
    @DisplayName("Encrypt and Decrypt consistency")
    void testEncryptAndDecryptConsistency() {
        String original = "ПРИВЕТ";
        String encrypted = cipher.processText(original, 7, true);
        String decrypted = cipher.processText(original, 7, false);
        assertEquals(original, decrypted);
    }
    @Test
    @DisplayName("Preserve case Encrypt")
    void testPreserveCase(){
        String text = "ПрИвЕт";
        String encrypted = cipher.processText(text, 5, true);
        String decrypted = cipher.processText(text, 5, false);
        assertEquals(text, decrypted);
    }
    @Test
    @DisplayName("Valid key")
    void testRussianValidKey(){
        assertTrue(cipher.validateKey(1));
        assertTrue(cipher.validateKey(10));
        assertTrue(cipher.validateKey(32));
    }
    @Test
    @DisplayName("Invalid russian key")
    void testRussianInvalidKey(){
        assertFalse(cipher.validateKey(0));
        assertFalse(cipher.validateKey(33));
        assertFalse(cipher.validateKey(-1));
    }
    @Test
    @DisplayName("Russian empty string")
    void testRussianEmptyString(){
        assertEquals("", cipher.processText("", 5, true));
    }
}
