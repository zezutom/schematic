package org.zezutom.schematic.model.json.schema;

import org.zezutom.schematic.model.EnumWithCustomValue;

/**
 * Supported data types. Each data type is a value of the 'type' keyword.
 * @see JsonSchemaAttribute
 */
public enum JsonDataType implements EnumWithCustomValue {
    STRING("string"),
    INTEGER("integer"),
    NUMBER("number"),
    OBJECT("object"),
    ARRAY("array"),
    BOOLEAN("boolean"),
    NULL("null"),
    ENUM("enum");

    private final String value;

    JsonDataType(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    public static boolean contains(String value) {
        return EnumWithCustomValue.contains(value, values());
    }

    public static JsonDataType get(String value) {
        return EnumWithCustomValue.get(value, values());
    }
}
