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
package org.giwi.nifi.client;

import org.giwi.nifi.client.api.ProcessGroupsApi;
import org.giwi.nifi.client.model.ProcessGroupEntity;
import org.giwi.nifi.client.model.ProcessGroupsEntity;

import java.util.List;

/**
 * Interactive mode for exploring NiFi instance.
 *
 * <p>Provides commands to:
 * <ul>
 *   <li>List all process groups</li>
 *   <li>Show process group details</li>
 *   <li>Explore pipeline structure</li>
 * </ul>
 *
 * <p>Usage example:
 * <pre>{@code
 * InteractiveMode mode = new InteractiveMode(tester.getClient());
 * mode.listProcessGroups("root");
 * }</pre>
 *
 * @author GiWi
 * @version 1.0-SNAPSHOT
 */
public class InteractiveMode {
    private final ProcessGroupsApi pgApi;

    /**
     * Creates an InteractiveMode with the specified ApiClient.
     *
     * @param client The ApiClient with active connection
     */
    public InteractiveMode(org.giwi.nifi.client.invoker.ApiClient client) {
        this.pgApi = new ProcessGroupsApi(client);
    }

    /**
     * Lists all process groups under a parent.
     *
     * @param parentGroupId The parent process group ID (use "root" for root)
     */
    public void listProcessGroups(String parentGroupId) {
        try {
            ProcessGroupsEntity groups = pgApi.getProcessGroups(parentGroupId);
            if (groups == null || groups.getProcessGroups() == null) {
                System.out.println("No process groups found under: " + parentGroupId);
                return;
            }

            var groupSet = groups.getProcessGroups();
            System.out.println("Process Groups under " + parentGroupId + " (" + groupSet.size() + " found):");
            System.out.println("");

            for (ProcessGroupEntity group : groupSet) {
                if (group.getComponent() != null) {
                    String id = group.getComponent().getId();
                    String name = group.getComponent().getName();
                    Integer runningCount = group.getComponent().getRunningCount();
                    System.out.println("  ID:   " + id);
                    System.out.println("  Name: " + name);
                    System.out.println("  Running: " + (runningCount != null ? runningCount : 0));
                    System.out.println("");
                }
            }
        } catch (Exception e) {
            System.err.println("Error listing process groups: " + e.getMessage());
        }
    }

    /**
     * Shows details of a specific process group.
     *
     * @param processGroupId The process group ID
     */
    public void showProcessGroup(String processGroupId) {
        try {
            ProcessGroupEntity group = pgApi.getProcessGroup(processGroupId);
            if (group == null || group.getComponent() == null) {
                System.out.println("Process group not found: " + processGroupId);
                return;
            }

            System.out.println("Process Group Details:");
            System.out.println("  ID:   " + processGroupId);
            System.out.println("  Name: " + group.getComponent().getName());
            System.out.println("  Parent: " + group.getComponent().getParentGroupId());
            System.out.println("  Running Count: " + group.getComponent().getRunningCount());
        } catch (Exception e) {
            System.err.println("Error showing process group: " + e.getMessage());
        }
    }
}
