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
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.FileWriter;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ConfigFileTest {

    private ConfigFile configFile;
    private File tempHome;

    @BeforeEach
    void setUp(@TempDir File tempDir) {
        configFile = new ConfigFile();
        tempHome = tempDir;
        System.setProperty("user.home", tempHome.getAbsolutePath());
    }

    @Test
    @DisplayName("Load non-existent config file returns empty map")
    void testLoadNonExistentConfig() {
        Map<String, String> config = configFile.load();
        assertNotNull(config);
        assertTrue(config.isEmpty());
    }

    @Test
    @DisplayName("Save and load config file")
    void testSaveAndLoadConfig() throws Exception {
        configFile.save("https://nifi:8443/nifi-api", "admin", "secret123");

        Map<String, String> config = configFile.load();
        assertEquals("https://nifi:8443/nifi-api", config.get("url"));
        assertEquals("admin", config.get("user"));
        assertEquals("secret123", config.get("pass"));
    }

    @Test
    @DisplayName("Save with null values uses defaults")
    void testSaveWithNulls() throws Exception {
        configFile.save(null, null, null);

        Map<String, String> config = configFile.load();
        assertEquals("https://localhost:8443/nifi-api", config.get("url"));
        assertEquals("admin", config.get("user"));
        assertEquals("admin", config.get("pass"));
    }

    @Test
    @DisplayName("Load config with missing password field")
    void testLoadConfigMissingPassword() throws Exception {
        File configFile = new File(tempHome, ".nifi-tester.yml");
        try (FileWriter fw = new FileWriter(configFile)) {
            fw.write("nifi:\n  url: https://nifi:8443\n  user: admin\n");
        }

        Map<String, String> config = this.configFile.load();
        assertEquals("https://nifi:8443", config.get("url"));
        assertEquals("admin", config.get("user"));
        assertNull(config.get("pass"));
    }

    @Test
    @DisplayName("Load config with missing nifi section")
    void testLoadConfigMissingNifiSection() throws Exception {
        File configFile = new File(tempHome, ".nifi-tester.yml");
        try (FileWriter fw = new FileWriter(configFile)) {
            fw.write("other: data\n");
        }

        Map<String, String> config = this.configFile.load();
        assertTrue(config.isEmpty());
    }

    @Test
    @DisplayName("Overwrite existing config")
    void testOverwriteConfig() throws Exception {
        configFile.save("https://old:8443", "olduser", "oldpass");
        configFile.save("https://new:8443", "newuser", "newpass");

        Map<String, String> config = configFile.load();
        assertEquals("https://new:8443", config.get("url"));
        assertEquals("newuser", config.get("user"));
        assertEquals("newpass", config.get("pass"));
    }

    @Test
    @DisplayName("Config file is created in user home directory")
    void testConfigFileLocation() throws Exception {
        configFile.save("https://test:8443", "test", "test");

        File expectedFile = new File(tempHome, ".nifi-tester.yml");
        assertTrue(expectedFile.exists());
        assertTrue(expectedFile.length() > 0);
    }
}
