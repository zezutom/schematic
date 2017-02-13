package org.zezutom.schematic.model.json.schema.properties;

import org.zezutom.schematic.model.json.schema.JsonDataType;

/**
 * Describes array properties.
 * @see JsonDataType
 */
public enum JsonArrayProperty {
    ITEMS("items"),
    ADDITIONAL_ITEMS("additionalItems"),
    MIN_ITEMS("minItems"),
    MAX_ITEMS("maxItems"),
    UNIQUE_ITEMS("uniqueItems");

    private String value;

    JsonArrayProperty(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static boolean contains(String fieldName) {
        return get(fieldName) != null;
    }

    public static JsonArrayProperty get(String value) {
        for (JsonArrayProperty property : values()) {
            if (property.getValue().equalsIgnoreCase(value)) {
                return property;
            }
        }
        return null;
    }
}
