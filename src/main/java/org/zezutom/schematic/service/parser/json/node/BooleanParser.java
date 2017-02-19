package org.zezutom.schematic.service.parser.json.node;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.zezutom.schematic.model.json.BooleanNode;
import org.zezutom.schematic.model.json.schema.JsonDataType;
import org.zezutom.schematic.service.PrototypedService;
import org.zezutom.schematic.service.generator.json.BooleanGenerator;
import org.zezutom.schematic.service.parser.json.JsonNodeParser;

/**
 * Parses a 'boolean' type of node.
 * @see JsonDataType
 */
@PrototypedService
public class BooleanParser implements JsonNodeParser<BooleanNode> {

    private final BooleanGenerator generator;

    @Autowired
    public BooleanParser(BooleanGenerator generator) {
        this.generator = generator;
    }

    @Override
    public BooleanNode parse(String nodeName, JsonNode node) {
        return new BooleanNode(nodeName, generator);
    }

    @Override
    public BooleanNode parse(JsonNode node) {
        return parse(null, node);
    }
}
