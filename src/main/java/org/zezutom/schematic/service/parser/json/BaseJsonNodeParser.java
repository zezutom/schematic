package org.zezutom.schematic.service.parser.json;

import com.fasterxml.jackson.databind.JsonNode;
import org.zezutom.schematic.model.Node;
import org.zezutom.schematic.model.json.JsonSchemaCombinationType;
import org.zezutom.schematic.service.generator.json.JsonSchemaGenerator;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Serves as a template for parsing a JSON node.
 */
public abstract class BaseJsonNodeParser<T, N extends Node<T, G>, P, G extends JsonSchemaGenerator<T>> implements JsonNodeParser<N> {

    abstract boolean isProperty(String fieldName);

    abstract P getProperty(String fieldName);

    abstract void parseProperty(@NotNull G generator, @NotNull P property, @NotNull JsonNode node);

    abstract N getNode(String nodeName, G generator);

    abstract G newGenerator();

    @Override
    public N parse(String nodeName, JsonNode node) {

        G generator = newGenerator();

        // Iterate over node's properties
        Iterator<String> fieldNameIterator = node.fieldNames();
        while (fieldNameIterator.hasNext()) {
            String fieldName = fieldNameIterator.next();
            if (isProperty(fieldName)) {
                P property = getProperty(fieldName);
                JsonNode propertyNode = node.get(fieldName);
                if (property == null || propertyNode == null) continue;
                parseProperty(generator, property, propertyNode);
            } else if (isCombinationType(fieldName)) {
                JsonNode combinationNode = node.get(fieldName);
                if (combinationNode != null && combinationNode.isArray()) {
                    JsonSchemaCombinationType type = JsonSchemaCombinationType.get(fieldName);
                    List<JsonNode> combinationNodes = new ArrayList<>();
                    Iterator<JsonNode> combinationNodeIterator = combinationNode.elements();
                    while (combinationNodeIterator.hasNext()) {
                        combinationNodes.add(combinationNodeIterator.next());
                    }
                    generator.combine(type, combinationNodes);
                }
            }
        }
        return getNode(nodeName, generator);
    }

    @Override
    public N parse(JsonNode node) {
        return parse(null, node);
    }

    private boolean isCombinationType(String fieldName) {
        return JsonSchemaCombinationType.contains(fieldName);
    }
}
