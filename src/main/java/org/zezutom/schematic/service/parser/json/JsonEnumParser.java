package org.zezutom.schematic.service.parser.json;

import com.fasterxml.jackson.databind.JsonNode;
import org.zezutom.schematic.model.LeafNode;
import org.zezutom.schematic.model.Node;
import org.zezutom.schematic.model.json.JsonDataType;
import org.zezutom.schematic.service.generator.value.EnumGenerator;

import javax.validation.constraints.NotNull;
import java.util.Iterator;

/**
 * Parses an 'enum' type of node.
 * @see org.zezutom.schematic.model.json.JsonDataType
 */
public class JsonEnumParser implements JsonLeafNodeParser<Object>  {

    private EnumGenerator generator;

    @Override
    public LeafNode<Object> parse(String nodeName, @NotNull JsonNode node) {

        if (node.isArray()) {
            JsonDataType dataType = generator.getDataType();
            Iterator<JsonNode> nodeIterator = node.elements();
            while (nodeIterator.hasNext()) {
                JsonNode childNode = nodeIterator.next();
                if (childNode == null) continue;

                if (dataType != null) {
                    // Only primitive data types are supported
                    switch (dataType) {
                        case INTEGER:
                            if (childNode.isInt()) {
                                generator.addItem(childNode.intValue());
                            }
                            break;
                        case NUMBER:
                            if (childNode.isNumber()) {
                                generator.addItem(childNode.numberValue());
                            }
                            break;
                        case STRING:
                            if (childNode.isTextual()) {
                                generator.addItem(childNode.textValue());
                            }
                            break;
                        case BOOLEAN:
                            if (childNode.isBoolean()) {
                                generator.addItem(childNode.booleanValue());
                            }
                            break;
                    }
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
        return new LeafNode<>(nodeName, generator);
    }

    @Override
    public Node parse(JsonNode node) {
        return null;
    }
}
