package org.zezutom.schematic.util;

import com.fasterxml.jackson.databind.JsonNode;
import org.zezutom.schematic.model.json.schema.JsonDataType;
import org.zezutom.schematic.model.json.schema.JsonSchemaAttribute;
import org.zezutom.schematic.model.json.schema.properties.JsonNumericProperty;
import org.zezutom.schematic.model.json.schema.properties.JsonStringProperty;

import java.util.Arrays;

/**
 * Helps with JSON traversal / parsing and value interpretation.
 */
public class JsonUtil {

    private JsonUtil() {
    }

    public static JsonDataType getDataType(JsonNode jsonNode) {
        if (jsonNode == null) return null;
        String typeFieldName = JsonSchemaAttribute.TYPE.getValue();

        JsonDataType dataType = null;
        if (jsonNode.has(typeFieldName)) {
            JsonNode typeNode = jsonNode.get(typeFieldName);
            if (typeNode != null) {
                dataType = JsonDataType.get(typeNode.textValue());
            }
        } else if (jsonNode.has(JsonDataType.ENUM.getValue())) {
            dataType = JsonDataType.ENUM;
        } else if (isString(jsonNode)) {
            dataType = JsonDataType.STRING;
        } else if (isNumber(jsonNode)) {
            dataType = JsonDataType.NUMBER;
        }

        return dataType;
    }

    private static boolean isString(JsonNode jsonNode) {
        return Arrays.stream(JsonStringProperty.values())
                .anyMatch(v -> jsonNode.has(v.getValue()));
    }

    private static boolean isNumber(JsonNode jsonNode) {
        return Arrays.stream(JsonNumericProperty.values())
                .anyMatch(v -> jsonNode.has(v.getValue()));
    }

}
