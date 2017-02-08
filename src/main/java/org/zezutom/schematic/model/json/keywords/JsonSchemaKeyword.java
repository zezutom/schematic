package org.zezutom.schematic.model.json.keywords;

/**
 * Basic attributes of a JSON schema, restricted to the purpose of this app.
 */
public enum JsonSchemaKeyword {
    ID("id"),
    TYPE("type"),
    TITLE("title"),
    DESCRIPTION("description"),
    DEFAULT("default");

    private String value;

    JsonSchemaKeyword(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
