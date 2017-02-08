package org.zezutom.schematic.service.parser.json;

import com.fasterxml.jackson.databind.JsonNode;
import org.zezutom.schematic.model.Node;

/**
 * Parses an 'array' type of node.
 * @see org.zezutom.schematic.model.json.JsonDataType
 */
public class JsonArrayParser implements JsonNodeParser {

    @Override
    public Node parse(JsonNode node) {
        return null;
    }

    @Override
    public Node parse(String name, JsonNode node) {
        return null;
    }
}
