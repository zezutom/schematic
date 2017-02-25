package org.zezutom.schematic.model.json.schema;

import org.zezutom.schematic.model.EnumWithCustomValue;

/**
 * Defines how two or more schemas can be combined together.
 */
public enum JsonSchemaCombinationType implements EnumWithCustomValue {
    ALL_OF("allOf"), ONE_OF("oneOf"), NOT("not");

    private final String value;

    JsonSchemaCombinationType(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    public static boolean contains(String value) {
        return EnumWithCustomValue.contains(value, values());
    }

    public static JsonSchemaCombinationType get(String value) {
        return EnumWithCustomValue.get(value, values());
    }
}
