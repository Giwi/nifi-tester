/*
 * Copyright 2026 GiWi
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.giwi.nifi.tester;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;

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
