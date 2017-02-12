package org.zezutom.schematic.service.parser.json;

import com.fasterxml.jackson.databind.JsonNode;
import org.zezutom.schematic.model.NullNode;
import org.zezutom.schematic.service.generator.json.NullGenerator;

/**
 * Parses a 'null' type of node.
 * @see org.zezutom.schematic.model.json.JsonDataType
 */
public class NullParser implements JsonNodeParser<NullNode> {

    private final NullGenerator generator = new NullGenerator(this);

    @Override
    public NullNode parse(String nodeName, JsonNode node) {
        return new NullNode(nodeName, generator);
    }

    @Override
    public NullNode parse(JsonNode node) {
        return parse(null, node);
    }
}
