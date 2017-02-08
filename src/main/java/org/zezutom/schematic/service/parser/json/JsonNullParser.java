package org.zezutom.schematic.service.parser.json;

import com.fasterxml.jackson.databind.JsonNode;
import org.zezutom.schematic.model.LeafNode;
import org.zezutom.schematic.model.Node;

import javax.validation.constraints.Null;

/**
 * Parses a 'null' type of node.
 * @see org.zezutom.schematic.model.json.JsonDataType
 */
public class JsonNullParser implements JsonLeafNodeParser<Null> {

    @Override
    public LeafNode<Null> parse(String nodeName, JsonNode node) {
        return null;
    }

    @Override
    public Node parse(JsonNode node) {
        return null;
    }
}
