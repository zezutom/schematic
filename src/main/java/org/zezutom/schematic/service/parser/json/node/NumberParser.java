package org.zezutom.schematic.service.parser.json.node;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.zezutom.schematic.model.json.NumberNode;
import org.zezutom.schematic.model.json.schema.properties.JsonNumericProperty;
import org.zezutom.schematic.model.json.schema.JsonDataType;
import org.zezutom.schematic.service.generator.json.NumberGenerator;

import javax.validation.constraints.NotNull;

/**
 * Parses numeric nodes.
 * @see JsonDataType
 */
@Service
@Scope("prototype")
public class NumberParser extends BaseJsonNodeParser<Number, NumberNode, JsonNumericProperty, NumberGenerator> {

    @Autowired
    public NumberParser(NumberGenerator generator) {
        super(generator);
    }

    @Override
    NumberNode getNode(String nodeName, NumberGenerator generator) {
        return new NumberNode(nodeName, generator);
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
    void parseProperty(@NotNull NumberGenerator generator, @NotNull JsonNumericProperty property, @NotNull JsonNode jsonNode) {
        switch (property) {
            case EXCLUSIVE_MAXIMUM:
                generator.setExclusiveMaximum(jsonNode.booleanValue());
                break;
            case EXCLUSIVE_MINIMUM:
                generator.setExclusiveMinimum(jsonNode.booleanValue());
                break;
            case MINIMUM:
                generator.setMinimum(jsonNode.numberValue());
                break;
            case MAXIMUM:
                generator.setMaximum(jsonNode.numberValue());
                break;
            case MULTIPLE_OF:
                generator.setMultipleOf(jsonNode.intValue());
                break;
        }
    }
}
