package org.zezutom.schematic.model.json.keywords;

/**
 * Defines how two or more schemas can be combined together.
 */
public enum JsonSchemaComboKeyword {
    ALL_OF("allOf"), ANY_OF("anyOf"), ONE_OF("oneOf"), NOT("not");

    private String value;

    JsonSchemaComboKeyword(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
