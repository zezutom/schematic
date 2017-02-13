package org.zezutom.schematic.service.parser.json;

import com.fasterxml.jackson.databind.JsonNode;
import org.zezutom.schematic.model.json.BooleanNode;
import org.zezutom.schematic.model.json.schema.JsonDataType;
import org.zezutom.schematic.service.generator.json.BooleanGenerator;

/**
 * Parses a 'boolean' type of node.
 * @see JsonDataType
 */
public class BooleanParser implements JsonNodeParser<BooleanNode> {

    private final BooleanGenerator generator = new BooleanGenerator();

    @Override
    public BooleanNode parse(String nodeName, JsonNode node) {
        return new BooleanNode(nodeName, generator);
    }

    @Override
    public BooleanNode parse(JsonNode node) {
        return parse(null, node);
    }
}
