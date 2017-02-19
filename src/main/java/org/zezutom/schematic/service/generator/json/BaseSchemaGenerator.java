package org.zezutom.schematic.service.generator.json;

import com.fasterxml.jackson.databind.JsonNode;
import org.zezutom.schematic.model.json.Node;
import org.zezutom.schematic.model.json.schema.JsonSchemaCombinationRule;
import org.zezutom.schematic.model.json.schema.JsonSchemaCombinationType;
import org.zezutom.schematic.service.generator.ValueGenerator;
import org.zezutom.schematic.service.parser.json.JsonNodeParser;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Base for generated values driven by a JSON schema.
 */
public abstract class BaseSchemaGenerator<T, N extends Node<T, G>, G extends ValueGenerator<T>, P extends JsonNodeParser<N>> implements JsonSchemaGenerator<T> {

    // Should this not be pushed to the ObjectGenerator only?
    private JsonSchemaCombinationRule<G> combinationRule;

    private P parser;

    public void setParser(P parser) {
        this.parser = parser;
    }

    @Override
    public void combine(JsonSchemaCombinationType combinationType, List<JsonNode> nodes) {
        if (combinationType == null || nodes == null || nodes.isEmpty()) return;
        List<G> generators = nodes
                                .stream()
                                .map(this::getGenerator)
                                .filter(Objects::nonNull)
                                .collect(Collectors.toList());
        combinationRule = new JsonSchemaCombinationRule<>(combinationType, generators);
    }

    public JsonSchemaCombinationRule<G> getCombinationRule() {
        return combinationRule;
    }

    private G getGenerator(JsonNode jsonNode) {
        if (parser == null) return null;

        N node = parser.parse(jsonNode);
        return (node == null) ? null : node.getValueGenerator();
    }
}
