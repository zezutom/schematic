package org.zezutom.schematic.service.parser.json;

import com.fasterxml.jackson.databind.JsonNode;
import org.zezutom.schematic.model.LeafNode;

/**
 * Parses primitive data types.
 * @see org.zezutom.schematic.model.json.JsonDataType
 * @see JsonNodeParser
 */
public interface JsonLeafNodeParser<T> extends JsonNodeParser {

    LeafNode<T> parse(String nodeName, JsonNode node);

}
