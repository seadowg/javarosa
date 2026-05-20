package org.javarosa.test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class TempFileUtils {

    static File createTempDir() {
        try {
            return Files.createTempDirectory("temp").toFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static File createTempFile(String suffix) {
        return createTempFile(null, suffix);
    }

    public static File createTempFile(File parent, String suffix) {
        try {
            File tempFile = File.createTempFile("file", suffix, parent);
            tempFile.deleteOnExit();
            return tempFile;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
