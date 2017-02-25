package org.zezutom.schematic.model.json.schema;

import org.zezutom.schematic.model.EnumWithCustomValue;

/**
 * Represents JSON schema meta fields (data type and value selection rules).
 */
public enum JsonSchemaAttribute implements EnumWithCustomValue {
    TYPE("type");

    private final String value;

    JsonSchemaAttribute(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    public static JsonSchemaAttribute get(String value) {
        return EnumWithCustomValue.get(value, values());
    }

    public static boolean contains(String value) {
        return EnumWithCustomValue.contains(value, values());
    }
}
