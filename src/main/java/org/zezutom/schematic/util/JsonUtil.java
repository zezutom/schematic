package org.zezutom.schematic.util;

import com.fasterxml.jackson.databind.JsonNode;
import org.zezutom.schematic.model.json.JsonDataType;
import org.zezutom.schematic.model.json.JsonSchemaAttribute;

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
        }
        return dataType;
    }
}
