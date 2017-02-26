package org.zezutom.schematic.service.parser.json;

import com.fasterxml.jackson.databind.JsonNode;
import org.zezutom.schematic.model.json.Node;
import org.zezutom.schematic.model.json.schema.JsonDataType;

/**
 * Parses a node in a JSON schema document. The node can be any of the supported types.
 * @see JsonDataType
 */
public interface JsonNodeParser<T extends Node> {

    T parse(JsonNode jsonNode);

    T parse(String name, JsonNode jsonNode);
}
