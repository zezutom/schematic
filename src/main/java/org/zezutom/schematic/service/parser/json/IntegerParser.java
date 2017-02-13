package org.zezutom.schematic.service.parser.json;

import com.fasterxml.jackson.databind.JsonNode;
import org.zezutom.schematic.model.json.IntegerNode;
import org.zezutom.schematic.model.json.schema.properties.JsonNumericProperty;
import org.zezutom.schematic.model.json.schema.JsonDataType;
import org.zezutom.schematic.service.generator.json.IntegerGenerator;

import javax.validation.constraints.NotNull;

/**
 * Parses an 'integer' type of node.
 * @see JsonDataType
 */
public class IntegerParser extends BaseJsonNodeParser<Integer, IntegerNode, JsonNumericProperty, IntegerGenerator> {

    @Override
    IntegerGenerator newGenerator() {
        return new IntegerGenerator(this);
    }

    @Override
    boolean isProperty(String fieldName) {
        return JsonNumericProperty.contains(fieldName);
    }

    @Override
    JsonNumericProperty getProperty(String fieldName) {
        return JsonNumericProperty.get(fieldName);
    }

    @Override
    void parseProperty(@NotNull IntegerGenerator generator, @NotNull JsonNumericProperty property, @NotNull JsonNode node) {
        if (JsonNumericProperty.MULTIPLE_OF.equals(property)) {
            generator.setMultipleOf(node.intValue());
        }
    }

    @Override
    IntegerNode getNode(String nodeName, IntegerGenerator generator) {
        return new IntegerNode(nodeName, generator);
    }
}
