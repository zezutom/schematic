package org.zezutom.schematic.service.parser.json;

import com.fasterxml.jackson.databind.JsonNode;
import org.zezutom.schematic.model.json.Node;
import org.zezutom.schematic.model.json.schema.JsonDataType;
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
        nodeParserMap.put(JsonDataType.ARRAY, ArrayParser::new);
        nodeParserMap.put(JsonDataType.BOOLEAN, BooleanParser::new);
        nodeParserMap.put(JsonDataType.ENUM, EnumParser::new);
        nodeParserMap.put(JsonDataType.INTEGER, IntegerParser::new);
        nodeParserMap.put(JsonDataType.NULL, NullParser::new);
        nodeParserMap.put(JsonDataType.NUMBER, NumberParser::new);
        nodeParserMap.put(JsonDataType.OBJECT, ObjectParser::new);
        nodeParserMap.put(JsonDataType.STRING, StringParser::new);
    }

    static <T extends Node> JsonNodeParser<T> newInstance(JsonNode node) {
        JsonDataType dataType = JsonUtil.getDataType(node);
        Supplier<JsonNodeParser> parserSupplier = nodeParserMap.get(dataType);
        return (parserSupplier == null) ? null : parserSupplier.get();
    }

}
