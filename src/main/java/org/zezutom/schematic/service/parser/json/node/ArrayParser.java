package org.zezutom.schematic.service.parser.json.node;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.zezutom.schematic.model.json.ArrayNode;
import org.zezutom.schematic.model.json.Node;
import org.zezutom.schematic.model.json.schema.JsonDataType;
import org.zezutom.schematic.model.json.schema.properties.JsonArrayProperty;
import org.zezutom.schematic.service.PrototypedService;
import org.zezutom.schematic.service.generator.json.ArrayGenerator;
import org.zezutom.schematic.service.parser.json.JsonNodeParser;
import org.zezutom.schematic.util.JsonUtil;

import javax.validation.constraints.NotNull;
import java.util.Iterator;
import java.util.List;

/**
 * Parses an 'array' type of node.
 * @see JsonDataType
 */
@PrototypedService
public class ArrayParser extends BaseJsonNodeParser<List<Object>, ArrayNode, JsonArrayProperty, ArrayGenerator> {

    private final JsonNodeParserFactory parserFactory;

    @Autowired
    public ArrayParser(ArrayGenerator generator, JsonNodeParserFactory parserFactory) {
        super(generator);
        this.parserFactory = parserFactory;
    }

    @Override
    boolean isProperty(String fieldName) {
        return JsonArrayProperty.contains(fieldName);
    }

    @Override
    JsonArrayProperty getProperty(String fieldName) {
        return JsonArrayProperty.get(fieldName);
    }

    @Override
    void parseProperty(@NotNull ArrayGenerator generator, @NotNull JsonArrayProperty property, @NotNull JsonNode node) {
        switch (property) {
            case ITEMS:
                if (node.isArray()) {
                    Iterator<JsonNode> nodeIterator = node.elements();
                    while (nodeIterator.hasNext()) {
                        addItem(generator, nodeIterator.next());
                    }
                } else {
                    addItem(generator, node);
                }
                break;
            case ADDITIONAL_ITEMS:
                generator.setAdditionalItems(node.booleanValue());
                break;
            case MAX_ITEMS:
                generator.setMaxItems(node.intValue());
                break;
            case MIN_ITEMS:
                generator.setMinItems(node.intValue());
                break;
            case UNIQUE_ITEMS:
                generator.setUniqueItems(node.booleanValue());
                break;
        }
    }

    private void addItem(@NotNull ArrayGenerator generator, @NotNull JsonNode jsonNode) {
        JsonDataType dataType = JsonUtil.getDataType(jsonNode);
        if (dataType != null) {
            JsonNodeParser parser = parserFactory.getInstance(jsonNode);
            if (parser != null) {
                Node node = parser.parse(jsonNode);
                if (node != null) {
                    generator.addItem(node.getValueGenerator());
                }
            }
        }
    }

    @Override
    ArrayNode getNode(String nodeName, ArrayGenerator generator) {
        return new ArrayNode(nodeName, generator);
    }
}
