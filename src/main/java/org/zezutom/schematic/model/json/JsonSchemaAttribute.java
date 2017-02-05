package org.zezutom.schematic.model.json;

/**
 * Represents JSON schema meta fields (data type and value selection rules).
 */
public enum JsonSchemaAttribute {
    TYPE("type"),
    ONE_OF("oneOf"),
    ENUM("enum"),
    PATTERN("pattern"),
    MIN("minimum");

    private String value;

    JsonSchemaAttribute(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static JsonSchemaAttribute getEnum(String name) {
        if (name == null || name.isEmpty()) {
            return null;
        }
        for (JsonSchemaAttribute field : values()) {
            if (field.getValue().equalsIgnoreCase(name)) {
                return field;
            }
        }
        return null;
    }
}
