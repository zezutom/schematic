package org.zezutom.schematic.service.parser.json;

import com.fasterxml.jackson.databind.JsonNode;
import org.zezutom.schematic.model.NullNode;
import org.zezutom.schematic.service.generator.value.NullGenerator;

/**
 * Parses a 'null' type of node.
 * @see org.zezutom.schematic.model.json.JsonDataType
 */
public class JsonNullParser implements JsonNodeParser<NullNode> {

    @Override
    public NullNode parse(String nodeName, JsonNode node) {
        return new NullNode(nodeName, new NullGenerator());
    }

    @Override
    public NullNode parse(JsonNode node) {
        return parse(null, node);
    }
}
