package org.zezutom.schematic.service.parser.json;

import com.fasterxml.jackson.databind.JsonNode;
import org.zezutom.schematic.model.json.JsonDataType;
import org.zezutom.schematic.util.JsonUtil;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Provides a specific parser based on a type of the provided JSON node.
 */
class JsonNodeParserFactory {

    private static final Map<JsonDataType, Supplier<JsonNodeParser>> nodeParserMap = new EnumMap<>(JsonDataType.class);
    static {
        nodeParserMap.put(JsonDataType.ARRAY, JsonArrayParser::new);
        nodeParserMap.put(JsonDataType.BOOLEAN, JsonBooleanParser::new);
        nodeParserMap.put(JsonDataType.ENUM, JsonEnumParser::new);
        nodeParserMap.put(JsonDataType.INTEGER, JsonIntegerParser::new);
        nodeParserMap.put(JsonDataType.NULL, JsonNullParser::new);
        nodeParserMap.put(JsonDataType.NUMBER, JsonNumberParser::new);
        nodeParserMap.put(JsonDataType.OBJECT, JsonObjectParser::new);
        nodeParserMap.put(JsonDataType.STRING, JsonStringParser::new);
    }

    static JsonNodeParser newInstance(JsonNode node) {
        JsonDataType dataType = JsonUtil.getDataType(node);
        Supplier<JsonNodeParser> parserSupplier = nodeParserMap.get(dataType);
        return (parserSupplier == null) ? null : parserSupplier.get();
    }

}
