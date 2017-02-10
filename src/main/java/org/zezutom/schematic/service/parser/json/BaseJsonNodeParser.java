package org.zezutom.schematic.service.parser.json;

import com.fasterxml.jackson.databind.JsonNode;
import org.zezutom.schematic.model.Node;

import javax.validation.constraints.NotNull;
import java.util.Iterator;

/**
 * Serves as a template for parsing a JSON node.
 */
public abstract class BaseJsonNodeParser<T extends Node, P> implements JsonNodeParser<T> {

    abstract boolean isProperty(String fieldName);

    abstract P getProperty(String fieldName);

    abstract void parseProperty(@NotNull P property, @NotNull JsonNode node);

    abstract T getNode(String nodeName);

    @Override
    public T parse(String nodeName, JsonNode node) {

        // Iterate over node's properties
        Iterator<String> fieldNameIterator = node.fieldNames();
        while (fieldNameIterator.hasNext()) {
            String fieldName = fieldNameIterator.next();
            if (isProperty(fieldName)) {
                P property = getProperty(fieldName);
                JsonNode propertyNode = node.get(fieldName);
                if (property == null || propertyNode == null) continue;
                parseProperty(property, propertyNode);
            }
        }
        return getNode(nodeName);
    }

    @Override
    public T parse(JsonNode node) {
        return parse(null, node);
    }
}
