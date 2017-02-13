package org.zezutom.schematic.model.json.schema;

import org.zezutom.schematic.model.json.schema.properties.JsonStringProperty;

/**
 * Supported string formats.
 * @see JsonStringProperty
 */
public enum JsonStringFormat {
    DATE_TIME("date-time"),
    EMAIL("email"),
    HOSTNAME("hostname"),
    IPV4("ipv4"),
    IPV6("ipv6"),
    URI("uri");

    private String value;

    JsonStringFormat(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
