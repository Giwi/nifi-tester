package org.giwi.nifi.client;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

class PipelineTesterValidationTest {

    @Test
    @DisplayName("Login with null username throws exception")
    void loginNullUsername() {
        PipelineTester tester = new PipelineTester("http://localhost:8080/nifi-api");
        assertThrows(IllegalArgumentException.class, () -> {
            tester.login(null, "password");
        });
    }

    @Test
    @DisplayName("Login with empty username throws exception")
    void loginEmptyUsername() {
        PipelineTester tester = new PipelineTester("http://localhost:8080/nifi-api");
        assertThrows(IllegalArgumentException.class, () -> {
            tester.login(" ", "password");
        });
    }

    @Test
    @DisplayName("Login with null password throws exception")
    void loginNullPassword() {
        PipelineTester tester = new PipelineTester("http://localhost:8080/nifi-api");
        assertThrows(IllegalArgumentException.class, () -> {
            tester.login("admin", null);
        });
    }

    @Test
    @DisplayName("Deploy null YAML file throws exception")
    void deployNullFile() {
        PipelineTester tester = new PipelineTester("http://localhost:8080/nifi-api");
        assertThrows(IllegalArgumentException.class, () -> {
            tester.deployPipeline((File) null);
        });
    }

    @Test
    @DisplayName("Deploy non-existent YAML file throws IOException")
    void deployNonExistentFile() {
        PipelineTester tester = new PipelineTester("http://localhost:8080/nifi-api");
        File fakeFile = new File("/tmp/nonexistent-pipeline.yaml");
        assertThrows(IOException.class, () -> {
            tester.deployPipeline(fakeFile);
        });
    }
}
