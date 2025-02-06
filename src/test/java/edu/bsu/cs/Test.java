package edu.bsu.cs;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Objects;

import org.junit.jupiter.api.Assertions;

public class Test {

    @org.junit.jupiter.api.Test
    public void testAccessToJsonFile() throws IOException {
        String jsonData = readSampleFileAsString();
        Assertions.assertNotNull(jsonData);
    }

    private String readSampleFileAsString() throws NullPointerException, IOException {
        try (InputStream sampleFile = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("sample.json")) {
            return new String(Objects.requireNonNull(sampleFile).readAllBytes(), Charset.defaultCharset());
        }
    }

}
