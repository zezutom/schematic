package org.zezutom.schematic.model.json.schema.properties;

import org.zezutom.schematic.model.EnumWithCustomValue;
import org.zezutom.schematic.model.json.schema.JsonDataType;

/**
 * Describes object properties.
 * @see JsonDataType
 */
public enum JsonObjectProperty implements EnumWithCustomValue {
    PROPERTIES("properties"),
    ADDITIONAL_PROPERTIES("additionalProperties"),
    REQUIRED("required"),
    MIN_PROPERTIES("minProperties"),
    MAX_PROPERTIES("maxProperties");

    private final String value;

    JsonObjectProperty(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    public static boolean contains(String value) {
        return EnumWithCustomValue.contains(value, values());
    }

    public static JsonObjectProperty get(String value) {
        return EnumWithCustomValue.get(value, values());
    }
}
