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

import java.util.List;
import java.util.Map;

/**
 * Typed representation of a processor in a pipeline.
 */
public class ProcessorConfig {
    private String id;
    private String type;
    private String name;
    private String state = "STOPPED";
    private Double x = 0.0;
    private Double y = 0.0;
    private String schedulingStrategy;
    private String schedulingPeriod;
    private Integer concurrentlySchedulableTaskCount;
    private Long runDurationMillis;
    private List<String> autoTerminatedRelationships;
    private Map<String, String> properties;
    private String parentGroupId = "root";

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public Double getX() { return x; }
    public void setX(Double x) { this.x = x; }

    public Double getY() { return y; }
    public void setY(Double y) { this.y = y; }

    public String getSchedulingStrategy() { return schedulingStrategy; }
    public void setSchedulingStrategy(String s) { this.schedulingStrategy = s; }

    public String getSchedulingPeriod() { return schedulingPeriod; }
    public void setSchedulingPeriod(String s) { this.schedulingPeriod = s; }

    public Integer getConcurrentlySchedulableTaskCount() { return concurrentlySchedulableTaskCount; }
    public void setConcurrentlySchedulableTaskCount(Integer c) { this.concurrentlySchedulableTaskCount = c; }

    public Long getRunDurationMillis() { return runDurationMillis; }
    public void setRunDurationMillis(Long r) { this.runDurationMillis = r; }

    public List<String> getAutoTerminatedRelationships() { return autoTerminatedRelationships; }
    public void setAutoTerminatedRelationships(List<String> a) { this.autoTerminatedRelationships = a; }

    public Map<String, String> getProperties() { return properties; }
    public void setProperties(Map<String, String> p) { this.properties = p; }

    public String getParentGroupId() { return parentGroupId; }
    public void setParentGroupId(String p) { this.parentGroupId = p; }
}
