package org.zezutom.schematic.service.parser.json;

import com.fasterxml.jackson.databind.JsonNode;
import org.zezutom.schematic.model.NullNode;

/**
 * Parses a 'null' type of node.
 * @see org.zezutom.schematic.model.json.JsonDataType
 */
public class JsonNullParser implements JsonNodeParser<NullNode> {

    @Override
    public NullNode parse(String nodeName, JsonNode node) {
        return null;
    }

    @Override
    public NullNode parse(JsonNode node) {
        return null;
    }
}
