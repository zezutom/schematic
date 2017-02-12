package org.zezutom.schematic.service.parser.json;

import com.fasterxml.jackson.databind.JsonNode;
import org.zezutom.schematic.model.ObjectNode;
import org.zezutom.schematic.model.json.JsonDataType;
import org.zezutom.schematic.model.json.properties.JsonObjectProperty;
import org.zezutom.schematic.service.generator.ObjectGenerator;
import org.zezutom.schematic.service.generator.ValueGenerator;
import org.zezutom.schematic.util.JsonUtil;

import javax.validation.constraints.NotNull;
import java.util.Iterator;

/**
 * Parses an 'object' type of node.
 * @see org.zezutom.schematic.model.json.JsonDataType
 */
class JsonObjectParser extends BaseJsonNodeParser<ObjectNode, JsonObjectProperty, ObjectGenerator> {

    JsonObjectParser() {
        super(new ObjectGenerator());
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
    void parseProperty(@NotNull JsonObjectProperty property, @NotNull JsonNode node) {
        switch (property) {
            case PROPERTIES:
                Iterator<String> fieldNameIterator = node.fieldNames();
                while (fieldNameIterator.hasNext()) {
                    String fieldName = fieldNameIterator.next();
                    generator.addProperty(fieldName, resolveGenerator(node.get(fieldName)));
                }
                break;
            case ADDITIONAL_PROPERTIES:
                if (node.isBoolean()) {
                    generator.setAdditionalPropertiesAllowed(node.booleanValue());
                } else {
                    JsonDataType dataType = JsonUtil.getDataType(node);
                    if (dataType != null) {
                        JsonNodeParser parser = JsonNodeParserFactory.newInstance(node);
                        if (parser != null) {
                            generator.addAdditionalGenerator(parser.getGenerator());
                        }
                    }
                }
                break;
            case MIN_PROPERTIES:
                generator.setMin(node.intValue());
                break;
            case MAX_PROPERTIES:
                generator.setMax(node.intValue());
                break;
            case REQUIRED:
                if (node.isArray()) {
                    for (JsonNode requiredNode : node) {
                        if (requiredNode.isTextual()) {
                            generator.addRequiredProperty(requiredNode.textValue());
                        }
                    }
                }
                break;
        }
    }

    @Override
    ObjectNode getNode(String nodeName) {
        return new ObjectNode(nodeName, generator);
    }

    private ValueGenerator resolveGenerator(JsonNode node) {
        if (node == null) return null;

        JsonNodeParser parser = JsonNodeParserFactory.newInstance(node);
        return (parser == null) ? null : parser.getGenerator();
    }
}
