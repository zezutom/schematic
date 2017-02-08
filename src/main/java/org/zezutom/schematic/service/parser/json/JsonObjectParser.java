package org.zezutom.schematic.service.parser.json;

import com.fasterxml.jackson.databind.JsonNode;
import org.zezutom.schematic.model.CompoundNode;
import org.zezutom.schematic.model.Node;
import org.zezutom.schematic.model.json.properties.JsonObjectProperty;

import java.util.Iterator;

/**
 * Parses an 'object' type of node.
 * @see org.zezutom.schematic.model.json.JsonDataType
 */
public class JsonObjectParser implements JsonNodeParser {

    @Override
    public Node parse(JsonNode jsonNode) {
        JsonNode propertiesNode = getPropertiesNode(jsonNode);
        if (propertiesNode == null) {
            return new CompoundNode();
        }

        CompoundNode node = new CompoundNode();
        Iterator<String> fieldNameIterator = propertiesNode.fieldNames();
        while (fieldNameIterator.hasNext()) {
            String fieldName = fieldNameIterator.next();
            JsonNode fieldNode = propertiesNode.get(fieldName);
            JsonNodeParser parser = JsonNodeParserFactory.newInstance(fieldNode);
            if (parser != null) {
                node.addNode(parser.parse(fieldName, fieldNode));
            }
        }
        return node;
    }

    @Override
    public Node parse(String name, JsonNode jsonNode) {
        CompoundNode node = new CompoundNode(name);
        node.addNode(parse(jsonNode));
        return node;
    }

    private JsonNode getPropertiesNode(JsonNode node) {
        if (node == null) return null;
        return node.get(JsonObjectProperty.PROPERTIES.getValue());
    }
}
