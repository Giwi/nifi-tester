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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Manages configuration file ~/.nifi-tester.yml.
 *
 * <p>Reads NiFi connection settings from a config file, allowing users to
 * avoid specifying --url, --user, --pass on every command.</p>
 *
 * <p>Config file format:
 * <pre>
 * nifi:
 *   url: https://localhost:8443/nifi-api
 *   user: admin
 *   password: admin123
 * </pre>
 * </p>
 *
 * @author GiWi
 * @version 1.0-SNAPSHOT
 */
public class ConfigFile {
    private static final String CONFIG_FILE = ".nifi-tester.yml";
    private final ObjectMapper mapper;

    public ConfigFile() {
        this.mapper = new ObjectMapper(new YAMLFactory());
        this.mapper.findAndRegisterModules();
    }

    /**
     * Loads configuration from ~/.nifi-tester.yml.
     *
     * @return Map containing nifi.url, nifi.user, nifi.pass if file exists
     */
    public Map<String, String> load() {
        Map<String, String> config = new HashMap<>();
        File configFile = new File(System.getProperty("user.home"), CONFIG_FILE);

        if (!configFile.exists()) {
            return config;
        }

        try {
            Map<String, Object> yaml = mapper.readValue(configFile, new TypeReference<Map<String, Object>>() {});
            if (yaml.containsKey("nifi")) {
                Map<String, Object> nifi = (Map<String, Object>) yaml.get("nifi");
                if (nifi.containsKey("url")) config.put("url", nifi.get("url").toString());
                if (nifi.containsKey("user")) config.put("user", nifi.get("user").toString());
                if (nifi.containsKey("password")) config.put("pass", nifi.get("password").toString());
            }
        } catch (IOException e) {
            // Config file not readable, return empty
        }

        return config;
    }

    /**
     * Saves configuration to ~/.nifi-tester.yml.
     *
     * @param url The NiFi API URL
     * @param user The username
     * @param password The password
     * @throws IOException if the file cannot be written
     */
    public void save(String url, String user, String password) throws IOException {
        File configFile = new File(System.getProperty("user.home"), CONFIG_FILE);

        Map<String, Object> yaml = new HashMap<>();
        Map<String, Object> nifi = new HashMap<>();
        nifi.put("url", url != null ? url : "https://localhost:8443/nifi-api");
        nifi.put("user", user != null ? user : "admin");
        nifi.put("password", password != null ? password : "admin");
        yaml.put("nifi", nifi);

        mapper.writeValue(configFile, yaml);
    }
}
