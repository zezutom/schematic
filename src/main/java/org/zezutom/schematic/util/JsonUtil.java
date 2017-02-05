package org.zezutom.schematic.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import org.zezutom.schematic.model.json.JsonSchemaAttribute;
import org.zezutom.schematic.service.generator.value.NumberGenerator;
import org.zezutom.schematic.service.generator.value.StringGenerator;

import java.util.Iterator;
import java.util.Optional;

/**
 * Helps with JSON traversal / parsing and value interpretation.
 */
public class JsonUtil {

    private JsonUtil() {}

    public static NumberGenerator createNumberGenerator(JsonNode node) {
        NumberGenerator generator = new NumberGenerator();
        if (isMinNode(node)) {
            JsonNode minNode = node.get(JsonSchemaAttribute.MIN.getValue());
            if (minNode != null && minNode.isNumber()) {
                generator.setMin(minNode.intValue());
            }
        } else {
            Iterator<JsonNode> nodeIterator = node.elements();
            while (nodeIterator.hasNext()) {
                JsonNode valueNode = nodeIterator.next();
                if (valueNode != null && valueNode.isNumber()) {
                    generator.addValue(valueNode.numberValue());
                }
            }
        }
        return generator;
    }

    public static StringGenerator createStringGenerator(JsonNode node) {
        StringGenerator generator = new StringGenerator();
        if (isPatternNode(node)) {
            JsonNode patternNode = node.get(JsonSchemaAttribute.PATTERN.getValue());
            if (patternNode != null && patternNode.isTextual()) {
                generator.addValue(patternNode.textValue(), true);
            }
        } else {
            Iterator<JsonNode> nodeIterator = node.elements();
            while (nodeIterator.hasNext()) {
                JsonNode valueNode = nodeIterator.next();
                if (valueNode.isTextual()) {
                    generator.addValue(valueNode.textValue());
                } else if (isPatternNode(valueNode)) {
                    JsonNode patternNode = valueNode.get(JsonSchemaAttribute.PATTERN.getValue());
                    if (patternNode != null && patternNode.isTextual()) {
                        generator.addValue(patternNode.textValue(), true);
                    }
                }
            }
        }
        return generator;
    }

    public static boolean isArray(JsonNode node) {
        return JsonNodeType.ARRAY.equals(node.getNodeType());
    }

    public static boolean isNumber(JsonNode node) {
        Optional<JsonNode> typeNode = getAny(node, JsonSchemaAttribute.TYPE);
        return typeNode.isPresent() && "number".equals(typeNode.get().textValue());
    }

    public static boolean isBoolean(JsonNode node) {
        Optional<JsonNode> typeNode = getAny(node, JsonSchemaAttribute.TYPE);
        return typeNode.isPresent() && "boolean".equals(typeNode.get().textValue());
    }

    public static boolean hasAttribute(JsonNode node, JsonSchemaAttribute attribute) {
        return !(node == null || attribute == null) && node.has(attribute.getValue());
    }

    public static Optional<JsonNode> getAny(JsonNode jsonNode, JsonSchemaAttribute... attributes) {
        if (attributes == null) {
            return Optional.empty();
        }

        Optional<JsonNode> jsonNodeOptional = Optional.empty();

        for (JsonSchemaAttribute attribute : attributes) {
            String fieldName = attribute.getValue();
            if (jsonNode.has(fieldName)) {
                jsonNodeOptional = Optional.of(jsonNode.get(fieldName));
                break;
            }
        }
        return jsonNodeOptional;
    }

    private static boolean isPatternNode(JsonNode node) {
        return hasAttribute(node, JsonSchemaAttribute.PATTERN);
    }

    private static boolean isMinNode(JsonNode node) {
        return hasAttribute(node, JsonSchemaAttribute.MIN);
    }
}
