package org.zezutom.schematic.model.json;

/**
 * Represents JSON schema meta fields (data type and value selection rules).
 */
public enum JsonSchemaAttribute {
    TYPE("type");

    private String value;

    JsonSchemaAttribute(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
