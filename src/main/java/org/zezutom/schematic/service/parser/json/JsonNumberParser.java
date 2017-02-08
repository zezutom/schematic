package org.zezutom.schematic.service.parser.json;

import com.fasterxml.jackson.databind.JsonNode;
import org.zezutom.schematic.model.json.properties.JsonNumericProperty;
import org.zezutom.schematic.service.generator.value.NumberGenerator;

import javax.validation.constraints.NotNull;

/**
 * Parses a 'number' type of node.
 * @see org.zezutom.schematic.model.json.JsonDataType
 */
public class JsonNumberParser extends BaseJsonLeafNodeParser<Number, JsonNumericProperty, NumberGenerator> {

    public JsonNumberParser() {
        super(new NumberGenerator());
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
    void parseProperty(@NotNull JsonNumericProperty property, @NotNull JsonNode node) {
        switch (property) {
            case EXCLUSIVE_MAXIMUM:
                generator.setExclusiveMaximum(node.booleanValue());
                break;
            case EXCLUSIVE_MINIMUM:
                generator.setExclusiveMinimum(node.booleanValue());
                break;
            case MINIMUM:
                generator.setMinimum(node.numberValue());
                break;
            case MAXIMUM:
                generator.setMaximum(node.numberValue());
                break;
            case MULTIPLE_OF:
                generator.setMultipleOf(node.intValue());
                break;
        }
    }
}
