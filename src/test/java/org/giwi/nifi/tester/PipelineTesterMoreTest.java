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

import org.giwi.nifi.tester.invoker.ApiClient;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.FileWriter;

import static org.junit.jupiter.api.Assertions.*;

class PipelineTesterMoreTest {

    private PipelineTester tester;

    @BeforeEach
    void setUp() {
        tester = new PipelineTester("https://localhost:8443/nifi-api");
    }

    @Test
    @DisplayName("Deploy pipeline with null file")
    void testDeployWithNullFile() {
        assertThrows(IllegalArgumentException.class,
            () -> tester.deployPipeline((File) null));
    }

    @Test
    @DisplayName("Deploy pipeline with non-existent YAML file throws IOException")
    void testDeployNonExistentFile() {
        File fakeFile = new File("/tmp/nonexistent-pipeline.yaml");
        assertThrows(java.io.IOException.class,
            () -> tester.deployPipeline(fakeFile));
    }

    @Test
    @DisplayName("Get access token before login returns null")
    void testGetAccessTokenBeforeLogin() {
        assertNull(tester.getAccessToken());
    }

    @Test
    @DisplayName("Set and get dry-run mode")
    void testSetDryRun() {
        tester.setDryRun(true);
        assertTrue(tester.isDryRun());

        tester.setDryRun(false);
        assertFalse(tester.isDryRun());
    }

    @Test
    @DisplayName("Get client returns valid ApiClient")
    void testGetClient() {
        ApiClient client = tester.getClient();
        assertNotNull(client);
        assertEquals("https://localhost:8443/nifi-api", client.getBasePath());
    }

    @Test
    @DisplayName("Close does not throw")
    void testClose() {
        assertDoesNotThrow(() -> tester.close());
    }
}
