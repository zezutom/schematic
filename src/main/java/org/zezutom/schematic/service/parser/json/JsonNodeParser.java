package org.zezutom.schematic.service.parser.json;

import com.fasterxml.jackson.databind.JsonNode;
import org.zezutom.schematic.model.Node;

/**
 * Parses a node in a JSON schema document. The node can be any of the supported types.
 * @see org.zezutom.schematic.model.json.JsonDataType
 */
public interface JsonNodeParser<T extends Node> {

    T parse(JsonNode node);

    T parse(String name, JsonNode node);
}
