package org.zezutom.schematic.model.json.schema.properties;

import org.zezutom.schematic.model.EnumWithCustomValue;
import org.zezutom.schematic.model.json.schema.JsonDataType;

/**
 * Describes array properties.
 * @see JsonDataType
 */
public enum JsonArrayProperty implements EnumWithCustomValue {
    ITEMS("items"),
    ADDITIONAL_ITEMS("additionalItems"),
    MIN_ITEMS("minItems"),
    MAX_ITEMS("maxItems"),
    UNIQUE_ITEMS("uniqueItems");

    private final String value;

    JsonArrayProperty(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    public static boolean contains(String value) {
        return EnumWithCustomValue.contains(value, values());
    }

    public static JsonArrayProperty get(String value) {
        return EnumWithCustomValue.get(value, values());
    }
}
