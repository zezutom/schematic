package org.zezutom.schematic.service.parser.json;

import com.fasterxml.jackson.databind.JsonNode;
import org.zezutom.schematic.model.json.Node;
import org.zezutom.schematic.model.json.ObjectNode;
import org.zezutom.schematic.model.json.schema.JsonDataType;
import org.zezutom.schematic.model.json.schema.properties.JsonObjectProperty;
import org.zezutom.schematic.service.generator.ValueGenerator;
import org.zezutom.schematic.service.generator.json.ObjectGenerator;
import org.zezutom.schematic.util.JsonUtil;

import javax.validation.constraints.NotNull;
import java.util.Iterator;

/**
 * Parses an 'object' type of node.
 * @see JsonDataType
 */
public class ObjectParser extends BaseJsonNodeParser<Object, ObjectNode, JsonObjectProperty, ObjectGenerator> {

    @Override
    ObjectGenerator newGenerator() {
        return new ObjectGenerator(this);
    }

    @Override
    boolean isProperty(String fieldName) {
        return JsonObjectProperty.contains(fieldName);
    }

    @Override
    JsonObjectProperty getProperty(String fieldName) {
        return JsonObjectProperty.get(fieldName);
    }

    @Override
    void parseProperty(@NotNull ObjectGenerator generator, @NotNull JsonObjectProperty property, @NotNull JsonNode jsonNode) {
        switch (property) {
            case PROPERTIES:
                Iterator<String> fieldNameIterator = jsonNode.fieldNames();
                while (fieldNameIterator.hasNext()) {
                    String fieldName = fieldNameIterator.next();
                    generator.addProperty(fieldName, resolveGenerator(jsonNode.get(fieldName)));
                }
                break;
            case ADDITIONAL_PROPERTIES:
                if (jsonNode.isBoolean()) {
                    generator.setAdditionalPropertiesAllowed(jsonNode.booleanValue());
                } else {
                    JsonDataType dataType = JsonUtil.getDataType(jsonNode);
                    if (dataType != null) {
                        JsonNodeParser parser = JsonNodeParserFactory.newInstance(jsonNode);
                        if (parser != null) {
                            Node node = parser.parse(jsonNode);
                            if (node != null) {
                                generator.addAdditionalGenerator(node.getValueGenerator());
                            }
                        }
                    }
                }
                break;
            case MIN_PROPERTIES:
                generator.setMin(jsonNode.intValue());
                break;
            case MAX_PROPERTIES:
                generator.setMax(jsonNode.intValue());
                break;
            case REQUIRED:
                if (jsonNode.isArray()) {
                    for (JsonNode requiredNode : jsonNode) {
                        if (requiredNode.isTextual()) {
                            generator.addRequiredProperty(requiredNode.textValue());
                        }
                    }
                }
                break;
        }
    }

    @Override
    ObjectNode getNode(String nodeName, ObjectGenerator generator) {
        return new ObjectNode(nodeName, generator);
    }

    private ValueGenerator resolveGenerator(JsonNode jsonNode) {
        if (jsonNode == null) return null;

        JsonNodeParser parser = JsonNodeParserFactory.newInstance(jsonNode);
        Node node = null;
        if (parser != null) {
            node = parser.parse(jsonNode);
        }
        return (node == null) ? null : node.getValueGenerator();
    }
}
