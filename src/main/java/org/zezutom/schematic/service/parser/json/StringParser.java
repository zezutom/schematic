package org.zezutom.schematic.service.parser.json;

import com.fasterxml.jackson.databind.JsonNode;
import org.zezutom.schematic.model.StringNode;
import org.zezutom.schematic.model.json.properties.JsonStringProperty;
import org.zezutom.schematic.service.generator.json.StringGenerator;

import javax.validation.constraints.NotNull;

/**
 * Parses a 'string' type of node.
 * @see org.zezutom.schematic.model.json.JsonDataType
 */
public class StringParser extends BaseJsonNodeParser<String, StringNode, JsonStringProperty, StringGenerator> {

    @Override
    StringGenerator newGenerator() {
        return new StringGenerator(this);
    }

    @Override
    StringNode getNode(String nodeName, StringGenerator generator) {
        return new StringNode(nodeName, generator);
    }

    @Override
    boolean isProperty(String fieldName) {
        return JsonStringProperty.contains(fieldName);
    }

    @Override
    JsonStringProperty getProperty(String fieldName) {
        return JsonStringProperty.get(fieldName);
    }

    @Override
    void parseProperty(@NotNull StringGenerator generator, @NotNull JsonStringProperty property, @NotNull JsonNode node) {
        switch(property) {
            case FORMAT:
                generator.setFormat(node.textValue());
                break;
            case MAX_LENGTH:
                generator.setMaxLength(node.intValue());
                break;
            case MIN_LENGTH:
                generator.setMinLength(node.intValue());
                break;
            case PATTERN:
                generator.setPattern(node.textValue());
                break;
        }
    }
}
