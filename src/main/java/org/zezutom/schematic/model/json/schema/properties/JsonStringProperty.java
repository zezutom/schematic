package org.zezutom.schematic.model.json.schema.properties;

import org.zezutom.schematic.model.json.schema.JsonDataType;

/**
 * Describes string properties.
 * @see JsonDataType
 */
public enum JsonStringProperty {
    MIN_LENGTH("minLength"),
    MAX_LENGTH("maxLength"),
    PATTERN("pattern"),
    FORMAT("format");

    private String value;

    JsonStringProperty(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static boolean contains(String value) {
        return get(value) != null;
    }

    public static JsonStringProperty get(String value) {
        for (JsonStringProperty property : values()) {
            if (property.getValue().equalsIgnoreCase(value)) {
                return property;
            }
        }
        return null;
    }
}
