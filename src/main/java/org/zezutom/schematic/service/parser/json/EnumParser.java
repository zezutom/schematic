package org.zezutom.schematic.service.parser.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.NullNode;
import org.zezutom.schematic.model.json.EnumNode;
import org.zezutom.schematic.model.json.schema.JsonDataType;
import org.zezutom.schematic.service.generator.json.EnumGenerator;

import javax.validation.constraints.NotNull;
import java.util.Iterator;

/**
 * Parses an 'enum' type of node.
 * @see JsonDataType
 */
public class EnumParser implements JsonNodeParser<EnumNode>  {

    private final EnumGenerator generator = new EnumGenerator();

    @Override
    public EnumNode parse(String nodeName, @NotNull JsonNode node) {
        JsonNode enumNode = node.get(JsonDataType.ENUM.getValue());
        if (enumNode == null) return null;

        if (enumNode.isArray()) {
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
                    } else if (childNode.isNumber()) {
                        generator.addItem(childNode.numberValue());
                    } else if (childNode.isTextual()) {
                        generator.addItem(childNode.textValue());
                    } else if (childNode.isBoolean()) {
                        generator.addItem(childNode.booleanValue());
                    }
                }
            }
        }
        return new EnumNode(nodeName, generator);
    }

    @Override
    public EnumNode parse(JsonNode node) {
        return parse(null, node);
    }

    private boolean isNullNode(JsonNode node) {
        return node == null || (node instanceof NullNode);
    }
}
