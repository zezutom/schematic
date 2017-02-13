package org.zezutom.schematic.model.json.schema.properties;

import org.zezutom.schematic.model.json.schema.JsonDataType;

/**
 * Describes numeric properties.
 * @see JsonDataType
 */
public enum JsonNumericProperty {
    MULTIPLE_OF("multipleOf"),
    MINIMUM("minimum"),
    MAXIMUM("maximum"),
    EXCLUSIVE_MINIMUM("exclusiveMinimum"),
    EXCLUSIVE_MAXIMUM("exclusiveMaximum");

    private String value;

    JsonNumericProperty(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static boolean contains(String fieldName) {
        return get(fieldName) != null;
    }

    public static JsonNumericProperty get(String value) {
        for (JsonNumericProperty property : values()) {
            if (property.getValue().equalsIgnoreCase(value)) {
                return property;
            }
        }
        return null;
    }
}
