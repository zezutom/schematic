package org.zezutom.schematic.model.json;

/**
 * Defines how two or more schemas can be combined together.
 */
public enum JsonSchemaCombinationType {
    ALL_OF("allOf"), ONE_OF("oneOf"), NOT("not");

    private String value;

    JsonSchemaCombinationType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static boolean contains(String value) {
        return get(value) != null;
    }

    public static JsonSchemaCombinationType get(String value) {
        for (JsonSchemaCombinationType type : values()) {
            if (type.getValue().equalsIgnoreCase(value)) {
                return type;
            }
        }
        return null;
    }
}
