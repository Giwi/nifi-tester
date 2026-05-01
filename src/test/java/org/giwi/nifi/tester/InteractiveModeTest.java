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

class InteractiveModeTest {

    @Test
    @DisplayName("InteractiveMode constructor works")
    void testConstructor() {
        ApiClient client = new ApiClient();
        assertDoesNotThrow(() -> new InteractiveMode(client));
    }

    @Test
    @DisplayName("List process groups handles null parent")
    void testListProcessGroupsNull() {
        ApiClient client = new ApiClient();
        InteractiveMode mode = new InteractiveMode(client);
        // This should handle exceptions gracefully
        assertDoesNotThrow(() -> mode.listProcessGroups(null));
    }

    @Test
    @DisplayName("Show process group handles null id")
    void testShowProcessGroupNull() {
        ApiClient client = new ApiClient();
        InteractiveMode mode = new InteractiveMode(client);
        // This should handle exceptions gracefully
        assertDoesNotThrow(() -> mode.showProcessGroup(null));
    }

    @Test
    @DisplayName("List process groups with root")
    void testListProcessGroupsRoot() {
        ApiClient client = new ApiClient();
        InteractiveMode mode = new InteractiveMode(client);
        assertDoesNotThrow(() -> mode.listProcessGroups("root"));
    }

    @Test
    @DisplayName("Show process group with invalid id")
    void testShowProcessGroupInvalid() {
        ApiClient client = new ApiClient();
        InteractiveMode mode = new InteractiveMode(client);
        assertDoesNotThrow(() -> mode.showProcessGroup("invalid-id"));
    }
}
