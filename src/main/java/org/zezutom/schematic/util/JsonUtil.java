package org.zezutom.schematic.util;

import com.fasterxml.jackson.databind.JsonNode;
import org.zezutom.schematic.model.json.schema.JsonDataType;
import org.zezutom.schematic.model.json.schema.JsonSchemaAttribute;
import org.zezutom.schematic.model.json.schema.properties.JsonStringProperty;

/**
 * Helps with JSON traversal / parsing and value interpretation.
 */
public class JsonUtil {

    private JsonUtil() {
    }

    public static JsonDataType getDataType(JsonNode node) {
        if (node == null) return null;
        String typeFieldName = JsonSchemaAttribute.TYPE.getValue();
        JsonDataType dataType = null;
        if (node.has(typeFieldName)) {
            JsonNode typeNode = node.get(typeFieldName);
            if (typeNode != null) {
                dataType = JsonDataType.get(typeNode.textValue());
            }
        } else if (node.has(JsonDataType.ENUM.getValue())) {
            dataType = JsonDataType.ENUM;
        } else if (node.has(JsonStringProperty.FORMAT.getValue()) ||
                node.has(JsonStringProperty.PATTERN.getValue())) {
            dataType = JsonDataType.STRING;
        }
        return dataType;
    }
}
