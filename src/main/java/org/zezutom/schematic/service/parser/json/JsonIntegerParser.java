package org.zezutom.schematic.service.parser.json;

import com.fasterxml.jackson.databind.JsonNode;
import org.zezutom.schematic.model.IntegerNode;
import org.zezutom.schematic.model.json.properties.JsonNumericProperty;
import org.zezutom.schematic.service.generator.value.IntegerGenerator;

import javax.validation.constraints.NotNull;

/**
 * Parses an 'integer' type of node.
 * @see org.zezutom.schematic.model.json.JsonDataType
 */
class JsonIntegerParser extends BaseJsonNodeParser<IntegerNode, JsonNumericProperty> {

    private final IntegerGenerator generator = new IntegerGenerator();

    @Override
    boolean isProperty(String fieldName) {
        return JsonNumericProperty.contains(fieldName);
    }

    @Override
    JsonNumericProperty getProperty(String fieldName) {
        return JsonNumericProperty.get(fieldName);
    }

    @Override
    void parseProperty(@NotNull JsonNumericProperty property, @NotNull JsonNode node) {
        if (JsonNumericProperty.MULTIPLE_OF.equals(property)) {
            generator.setMultipleOf(node.intValue());
        }
    }

    @Override
    IntegerNode getNode(String nodeName) {
        return new IntegerNode(nodeName, generator);
    }
}
