package org.zezutom.schematic.service.parser.json.node;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zezutom.schematic.model.json.NullNode;
import org.zezutom.schematic.model.json.schema.JsonDataType;
import org.zezutom.schematic.service.generator.json.NullGenerator;
import org.zezutom.schematic.service.parser.json.JsonNodeParser;

/**
 * Parses a 'null' type of node.
 * @see JsonDataType
 */
@Service
public class NullParser implements JsonNodeParser<NullNode> {

    private final NullGenerator generator;

    @Autowired
    public NullParser(NullGenerator generator) {
        this.generator = generator;
    }

    @Override
    public NullNode parse(String nodeName, JsonNode jsonNode) {
        if (jsonNode == null) return null;
        return new NullNode(nodeName, generator);
    }

    @Override
    public NullNode parse(JsonNode jsonNode) {
        return parse(null, jsonNode);
    }
}
