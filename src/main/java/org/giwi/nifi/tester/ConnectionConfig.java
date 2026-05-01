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
 * Typed representation of a connection in a pipeline.
 */
public class ConnectionConfig {
    private String id;
    private String name;
    private String sourceId;
    private String sourceType = "PROCESSOR";
    private String destinationId;
    private String destinationType = "PROCESSOR";
    private List<String> selectedRelationships;
    private String flowFileExpiration = "0 ms";
    private String backPressureDataSizeThreshold = "1 GB";
    private Long backPressureObjectThreshold = 10000L;
    private Double x = 0.0;
    private Double y = 0.0;
    private List<Map<String, Double>> bends;
    private String parentGroupId = "root";

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSourceId() { return sourceId; }
    public void setSourceId(String s) { this.sourceId = s; }

    public String getSourceType() { return sourceType; }
    public void setSourceType(String s) { this.sourceType = s; }

    public String getDestinationId() { return destinationId; }
    public void setDestinationId(String d) { this.destinationId = d; }

    public String getDestinationType() { return destinationType; }
    public void setDestinationType(String d) { this.destinationType = d; }

    public List<String> getSelectedRelationships() { return selectedRelationships; }
    public void setSelectedRelationships(List<String> r) { this.selectedRelationships = r; }

    public String getFlowFileExpiration() { return flowFileExpiration; }
    public void setFlowFileExpiration(String f) { this.flowFileExpiration = f; }

    public String getBackPressureDataSizeThreshold() { return backPressureDataSizeThreshold; }
    public void setBackPressureDataSizeThreshold(String b) { this.backPressureDataSizeThreshold = b; }

    public Long getBackPressureObjectThreshold() { return backPressureObjectThreshold; }
    public void setBackPressureObjectThreshold(Long b) { this.backPressureObjectThreshold = b; }

    public Double getX() { return x; }
    public void setX(Double x) { this.x = x; }

    public Double getY() { return y; }
    public void setY(Double y) { this.y = y; }

    public List<Map<String, Double>> getBends() { return bends; }
    public void setBends(List<Map<String, Double>> b) { this.bends = b; }

    public String getParentGroupId() { return parentGroupId; }
    public void setParentGroupId(String p) { this.parentGroupId = p; }
}
