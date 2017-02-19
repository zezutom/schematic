package org.zezutom.schematic.service.parser.json.node;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.zezutom.schematic.model.json.StringNode;
import org.zezutom.schematic.model.json.schema.properties.JsonStringProperty;
import org.zezutom.schematic.model.json.schema.JsonDataType;
import org.zezutom.schematic.service.generator.json.StringGenerator;

import javax.validation.constraints.NotNull;

/**
 * Parses a 'string' type of node.
 * @see JsonDataType
 */
@Service
@Scope("prototype")
public class StringParser extends BaseJsonNodeParser<String, StringNode, JsonStringProperty, StringGenerator> {

    @Autowired
    public StringParser(StringGenerator generator) {
        super(generator);
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
