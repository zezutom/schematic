package org.zezutom.schematic.service.parser.json;

import com.fasterxml.jackson.databind.JsonNode;
import org.zezutom.schematic.model.LeafNode;
import org.zezutom.schematic.service.generator.value.BooleanGenerator;

/**
 * Parses a 'boolean' type of node.
 * @see org.zezutom.schematic.model.json.JsonDataType
 */
public class JsonBooleanParser implements JsonLeafNodeParser<Boolean> {

    @Override
    public LeafNode<Boolean> parse(String nodeName, JsonNode node) {
        return new LeafNode<>(nodeName, new BooleanGenerator());
    }

    @Override
    public LeafNode<Boolean> parse(JsonNode node) {
        return parse(null, node);
    }
}
