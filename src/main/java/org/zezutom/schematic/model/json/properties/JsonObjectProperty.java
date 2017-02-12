package org.zezutom.schematic.model.json.properties;

import org.zezutom.schematic.model.json.JsonDataType;

/**
 * Describes object properties.
 * @see JsonDataType
 */
public enum JsonObjectProperty {
    PROPERTIES("properties"),
    ADDITIONAL_PROPERTIES("additionalProperties"),
    REQUIRED("required"),
    MIN_PROPERTIES("minProperties"),
    MAX_PROPERTIES("maxProperties");

    private String value;

    JsonObjectProperty(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static boolean contains(String value) {
        return get(value) != null;
    }

    public static JsonObjectProperty get(String value) {
        for (JsonObjectProperty property : values()) {
            if (property.getValue().equalsIgnoreCase(value)) {
                return property;
            }
        }
        return null;
    }
}
