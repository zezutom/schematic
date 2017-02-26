package org.zezutom.schematic.service.parser.json.node;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.zezutom.schematic.model.json.Node;
import org.zezutom.schematic.model.json.ObjectNode;
import org.zezutom.schematic.model.json.schema.JsonDataType;
import org.zezutom.schematic.model.json.schema.properties.JsonObjectProperty;
import org.zezutom.schematic.service.generator.ValueGenerator;
import org.zezutom.schematic.service.generator.json.ObjectGenerator;
import org.zezutom.schematic.service.parser.json.JsonNodeParser;
import org.zezutom.schematic.util.JsonUtil;

import javax.validation.constraints.NotNull;
import java.util.Iterator;
import java.util.Map;

/**
 * Parses an 'object' type of node.
 * @see JsonDataType
 */
@Service
@Scope("prototype")
public class ObjectParser extends BaseJsonNodeParser<Map<String, Object>, ObjectNode, JsonObjectProperty, ObjectGenerator> {

    private final JsonNodeParserFactory parserFactory;

    @Autowired
    public ObjectParser(ObjectGenerator generator, JsonNodeParserFactory parserFactory) {
        super(generator);
        this.parserFactory = parserFactory;
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
                        JsonNodeParser parser = parserFactory.getInstance(jsonNode);
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
                for (JsonNode requiredNode : jsonNode) {
                    generator.addRequiredProperty(requiredNode.textValue());
                }
                break;
        }
    }

    @Override
    ObjectNode getNode(String nodeName, ObjectGenerator generator) {
        return new ObjectNode(nodeName, generator);
    }

    ValueGenerator resolveGenerator(JsonNode jsonNode) {
        if (jsonNode == null) return null;

        JsonNodeParser parser = parserFactory.getInstance(jsonNode);
        Node node = null;
        if (parser != null) {
            node = parser.parse(jsonNode);
        }
        return (node == null) ? null : node.getValueGenerator();
    }
}
