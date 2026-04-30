package org.giwi.nifi.client;

import java.util.Map;

/**
 * Typed representation of a Controller Service in a pipeline.
 */
public class ControllerServiceConfig {
    private String id;
    private String type;
    private String name;
    private String state = "DISABLED";
    private String comments;
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

    public String getComments() { return comments; }
    public void setComments(String comments) { this.comments = comments; }

    public Map<String, String> getProperties() { return properties; }
    public void setProperties(Map<String, String> properties) { this.properties = properties; }

    public String getParentGroupId() { return parentGroupId; }
    public void setParentGroupId(String parentGroupId) { this.parentGroupId = parentGroupId; }
}
