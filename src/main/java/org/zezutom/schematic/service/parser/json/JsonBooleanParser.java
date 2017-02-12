package org.zezutom.schematic.service.parser.json;

import com.fasterxml.jackson.databind.JsonNode;
import org.zezutom.schematic.model.BooleanNode;
import org.zezutom.schematic.service.generator.BooleanGenerator;

/**
 * Parses a 'boolean' type of node.
 * @see org.zezutom.schematic.model.json.JsonDataType
 */
public class JsonBooleanParser implements JsonNodeParser<BooleanNode, BooleanGenerator> {

    private final BooleanGenerator generator = new BooleanGenerator();

    @Override
    public BooleanNode parse(String nodeName, JsonNode node) {
        return new BooleanNode(nodeName, generator);
    }

    @Override
    public BooleanNode parse(JsonNode node) {
        return parse(null, node);
    }

    @Override
    public BooleanGenerator getGenerator() {
        return generator;
    }
}
