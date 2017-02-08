package org.zezutom.schematic.service.parser.json;

import com.fasterxml.jackson.databind.JsonNode;
import org.zezutom.schematic.model.Node;

/**
 * Parses a node in a JSON schema document. The node can be any of the supported types.
 * @see org.zezutom.schematic.model.json.JsonDataType
 */
public interface JsonNodeParser {

    Node parse(JsonNode node);

    Node parse(String name, JsonNode node);
}
