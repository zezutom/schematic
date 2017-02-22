package org.zezutom.schematic.service.parser.json.node;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.zezutom.schematic.model.json.Node;
import org.zezutom.schematic.model.json.schema.JsonDataType;
import org.zezutom.schematic.service.parser.json.JsonNodeParser;
import org.zezutom.schematic.util.JsonUtil;

import java.util.Map;

/**
 * Provides a specific parser based on a type of the provided JSON node.
 */
@Service
public class JsonNodeParserFactory {

    private final Map<JsonDataType, Class<? extends JsonNodeParser>> nodeParserMap;

    private ApplicationContext context;

    public JsonNodeParserFactory(Map<JsonDataType, Class<? extends JsonNodeParser>> nodeParserMap) {
        this.nodeParserMap = nodeParserMap;
    }

    @Autowired
    public void setContext(ApplicationContext context) {
        this.context = context;
    }

    public<T extends Node, P extends JsonNodeParser<T>> P getInstance(JsonNode jsonNode) {
        JsonDataType dataType = JsonUtil.getDataType(jsonNode);
        if (dataType == null) return null;

        Class<? extends JsonNodeParser> parserClass = nodeParserMap.get(dataType);
        return (parserClass == null) ? null : (P) context.getBean(parserClass);
    }

}
