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

import static org.junit.jupiter.api.Assertions.*;

class PipelineTesterUnitTest {

    private PipelineTester tester;

    @Test
    @DisplayName("Constructor with URL only")
    void testConstructorWithUrl() {
        assertDoesNotThrow(() -> new PipelineTester("https://localhost:8443/nifi-api"));
    }

    @Test
    @DisplayName("Set and check dry-run mode")
    void testDryRunMode() {
        tester = new PipelineTester("https://localhost:8443/nifi-api");
        assertFalse(tester.isDryRun());

        tester.setDryRun(true);
        assertTrue(tester.isDryRun());

        tester.setDryRun(false);
        assertFalse(tester.isDryRun());
    }

    @Test
    @DisplayName("Close method does not throw")
    void testClose() {
        tester = new PipelineTester("https://localhost:8443/nifi-api");
        assertDoesNotThrow(() -> tester.close());
    }

    @Test
    @DisplayName("Get access token returns null before login")
    void testGetAccessTokenBeforeLogin() {
        tester = new PipelineTester("https://localhost:8443/nifi-api");
        // Before login, token should be null
        assertNull(tester.getAccessToken());
    }

    @Test
    @DisplayName("Constructor with URL, username, password")
    void testConstructorWithCredentials() {
        // This will try to login, so we expect an exception since there's no real NiFi
        assertThrows(Exception.class, () ->
            new PipelineTester("https://localhost:8443/nifi-api", "admin", "admin"));
    }

    @Test
    @DisplayName("Get client returns ApiClient")
    void testGetClient() {
        tester = new PipelineTester("https://localhost:8443/nifi-api");
        assertNotNull(tester.getClient());
        assertTrue(tester.getClient() instanceof ApiClient);
    }
}
