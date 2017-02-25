package org.zezutom.schematic.model.json.schema;

import org.zezutom.schematic.model.EnumWithCustomValue;
import org.zezutom.schematic.model.json.schema.properties.JsonStringProperty;

/**
 * Supported string formats.
 * @see JsonStringProperty
 */
public enum JsonStringFormat implements EnumWithCustomValue {
    DATE_TIME("date-time"),
    EMAIL("email"),
    HOSTNAME("hostname"),
    IPV4("ipv4"),
    IPV6("ipv6"),
    URI("uri");

    private final String value;

    JsonStringFormat(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    public static JsonStringFormat get(String value) {
        return EnumWithCustomValue.get(value, values());
    }

    public static boolean contains(String value) {
        return EnumWithCustomValue.contains(value, values());
    }

}
