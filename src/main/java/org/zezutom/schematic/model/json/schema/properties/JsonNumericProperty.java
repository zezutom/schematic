package org.zezutom.schematic.model.json.schema.properties;

import org.zezutom.schematic.model.EnumWithCustomValue;
import org.zezutom.schematic.model.json.schema.JsonDataType;

/**
 * Describes numeric properties.
 * @see JsonDataType
 */
public enum JsonNumericProperty implements EnumWithCustomValue {
    MULTIPLE_OF("multipleOf"),
    MINIMUM("minimum"),
    MAXIMUM("maximum"),
    EXCLUSIVE_MINIMUM("exclusiveMinimum"),
    EXCLUSIVE_MAXIMUM("exclusiveMaximum");

    private final String value;

    JsonNumericProperty(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    public static boolean contains(String value) {
        return EnumWithCustomValue.contains(value, values());
    }

    public static JsonNumericProperty get(String value) {
        return EnumWithCustomValue.get(value, values());
    }
}
