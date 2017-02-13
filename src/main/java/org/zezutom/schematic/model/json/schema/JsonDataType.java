package org.zezutom.schematic.model.json.schema;

/**
 * Supported data types. Each data type is a value of the 'type' keyword.
 * @see JsonSchemaAttribute
 */
public enum JsonDataType {
    STRING("string"),
    INTEGER("integer"),
    NUMBER("number"),
    OBJECT("object"),
    ARRAY("array"),
    BOOLEAN("boolean"),
    NULL("null"),
    ENUM("enum");

    private String value;

    JsonDataType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static boolean contains(String value) {
        return get(value) != null;
    }

    public static JsonDataType get(String value) {
        for (JsonDataType dataType : values()) {
            if (dataType.getValue().equalsIgnoreCase(value)) {
                return dataType;
            }
        }
        return null;
    }
}
