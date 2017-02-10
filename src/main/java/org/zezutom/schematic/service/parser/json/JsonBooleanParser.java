package org.zezutom.schematic.service.parser.json;

import com.fasterxml.jackson.databind.JsonNode;
import org.zezutom.schematic.model.BooleanNode;
import org.zezutom.schematic.service.generator.value.BooleanGenerator;

/**
 * Parses a 'boolean' type of node.
 * @see org.zezutom.schematic.model.json.JsonDataType
 */
public class JsonBooleanParser implements JsonNodeParser<BooleanNode> {

    @Override
    public BooleanNode parse(String nodeName, JsonNode node) {
        return new BooleanNode(nodeName, new BooleanGenerator());
    }

    @Override
    public BooleanNode parse(JsonNode node) {
        return parse(null, node);
    }
}
