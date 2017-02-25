package org.zezutom.schematic.model.json.schema.properties;

import org.zezutom.schematic.model.EnumWithCustomValue;
import org.zezutom.schematic.model.json.schema.JsonDataType;

/**
 * Describes string properties.
 * @see JsonDataType
 */
public enum JsonStringProperty implements EnumWithCustomValue {
    MIN_LENGTH("minLength"),
    MAX_LENGTH("maxLength"),
    PATTERN("pattern"),
    FORMAT("format");

    private final String value;

    JsonStringProperty(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    public static boolean contains(String value) {
        return EnumWithCustomValue.contains(value, values());
    }

    public static JsonStringProperty get(String value) {
        return EnumWithCustomValue.get(value, values());
    }
}
