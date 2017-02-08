package org.zezutom.schematic.model.json.properties;

import org.zezutom.schematic.model.json.JsonDataType;

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
}
