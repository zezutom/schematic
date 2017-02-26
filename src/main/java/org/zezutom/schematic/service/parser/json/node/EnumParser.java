package org.zezutom.schematic.service.parser.json.node;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.NullNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.zezutom.schematic.model.json.EnumNode;
import org.zezutom.schematic.model.json.schema.JsonDataType;
import org.zezutom.schematic.service.PrototypedService;
import org.zezutom.schematic.service.generator.json.EnumGenerator;
import org.zezutom.schematic.service.parser.json.JsonNodeParser;

import java.util.Iterator;

/**
 * Parses an 'enum' type of node.
 * @see JsonDataType
 */
@PrototypedService
public class EnumParser implements JsonNodeParser<EnumNode> {

    private final EnumGenerator generator;

    @Autowired
    public EnumParser(EnumGenerator generator) {
        this.generator = generator;
    }

    @Override
    public EnumNode parse(String nodeName, JsonNode jsonNode) {
        if (jsonNode == null) return null;
        JsonNode enumNode = jsonNode.get(JsonDataType.ENUM.getValue());
        if (enumNode == null) return null;

        Iterator<JsonNode> nodeIterator = enumNode.elements();
        while (nodeIterator.hasNext()) {
            JsonNode childNode = nodeIterator.next();
            if (isNullNode(childNode)) {
                // null is a valid value
                generator.addItem(null);
            } else {
                // Data type is unconstrained by the schema, but we only support primitive data types
                if (childNode.isInt()) {
                    generator.addItem(childNode.intValue());
                } else if(childNode.isFloatingPointNumber()) {
                    generator.addItem(childNode.doubleValue());
                } else if (childNode.isTextual()) {
                    generator.addItem(childNode.textValue());
                } else if (childNode.isBoolean()) {
                    generator.addItem(childNode.booleanValue());
                }
            }
        }
        return new EnumNode(nodeName, generator);
    }

    @Override
    public EnumNode parse(JsonNode jsonNode) {
        return parse(null, jsonNode);
    }

    private boolean isNullNode(JsonNode node) {
        return node == null || (node instanceof NullNode);
    }
}
