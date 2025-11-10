package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.AfterEach;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FileUtilTest {

    private final String testFile = "test_file.txt";

    @AfterEach
    void cleanup(){
        new File(testFile).delete();
    }
    @Test
    @DisplayName("Write to file and read back same content")
    void testWriteAndReadFile() throws IOException {
        String content = "Hello World";
        FileUtil.writeFile(testFile, content);

        String read = FileUtil.readFile(testFile);
        assertEquals(content, read);
    }
    @Test
    @DisplayName("Read non-existing file throws IOException")
    void testReadNonExistingFile() {
        assertThrows(IOException.class, () -> FileUtil.readFile("no_such_file.txt"));
    }
    @Test
    @DisplayName("Write empty string should create empty file")
    void testWriteEmpty() throws IOException{
        FileUtil.writeFile(testFile, "");
        String result = FileUtil.readFile(testFile);
        assertEquals("", result);
    }

}
