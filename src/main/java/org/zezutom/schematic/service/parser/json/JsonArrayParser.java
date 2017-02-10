package org.zezutom.schematic.service.parser.json;

import com.fasterxml.jackson.databind.JsonNode;
import org.zezutom.schematic.model.json.ArrayNode;
import org.zezutom.schematic.model.json.JsonDataType;
import org.zezutom.schematic.model.json.properties.JsonArrayProperty;
import org.zezutom.schematic.service.generator.value.ArrayGenerator;
import org.zezutom.schematic.util.JsonUtil;

import javax.validation.constraints.NotNull;
import java.util.Iterator;

/**
 * Parses an 'array' type of node.
 * @see org.zezutom.schematic.model.json.JsonDataType
 */
class JsonArrayParser extends BaseJsonNodeParser<ArrayNode, JsonArrayProperty> {

    private final ArrayGenerator generator = new ArrayGenerator();

    @Override
    boolean isProperty(String fieldName) {
        return JsonArrayProperty.contains(fieldName);
    }

    @Override
    JsonArrayProperty getProperty(String fieldName) {
        return JsonArrayProperty.get(fieldName);
    }

    @Override
    void parseProperty(@NotNull JsonArrayProperty property, @NotNull JsonNode node) {
        switch (property) {
            case ITEMS:
                if (node.isArray()) {
                    Iterator<JsonNode> nodeIterator = node.elements();
                    while (nodeIterator.hasNext()) {
                        addItem(nodeIterator.next());
                    }
                } else {
                    addItem(node);
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

    private void addItem(@NotNull JsonNode node) {
        JsonDataType dataType = JsonUtil.getDataType(node);
        if (dataType != null) {
            generator.addItem(dataType);
        }
    }

    @Override
    ArrayNode getNode(String nodeName) {
        return new ArrayNode(nodeName, generator);
    }
}
