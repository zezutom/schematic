package org.zezutom.schematic.service.generator.json;

import com.fasterxml.jackson.databind.JsonNode;
import org.zezutom.schematic.model.json.Node;
import org.zezutom.schematic.model.json.schema.JsonSchemaCombinationRule;
import org.zezutom.schematic.model.json.schema.JsonSchemaCombinationType;
import org.zezutom.schematic.service.generator.ValueGenerator;
import org.zezutom.schematic.service.parser.json.JsonNodeParser;
import org.zezutom.schematic.service.parser.json.node.JsonNodeParserFactory;
import org.zezutom.schematic.util.RandomUtil;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Base for generated values driven by a JSON schema.
 */
abstract class BaseSchemaGenerator<T, N extends Node<T, G>, G extends ValueGenerator<T>, P extends JsonNodeParser<N>> implements JsonSchemaGenerator<T, G> {

    JsonSchemaCombinationRule<G> combinationRule;

    private final JsonNodeParserFactory parserFactory;

    BaseSchemaGenerator(JsonNodeParserFactory parserFactory) {
        this.parserFactory = parserFactory;
    }

    public JsonNodeParserFactory getParserFactory() {
        return parserFactory;
    }

    @Override
    public void combine(JsonSchemaCombinationType type, List<JsonNode> jsonNodes) {
        if (type == null || jsonNodes == null || jsonNodes.isEmpty()) return;
        List<G> generators = jsonNodes
                                .stream()
                                .map(this::getGenerator)
                                .filter(Objects::nonNull)
                                .collect(Collectors.toList());
        if (!generators.isEmpty()) {
            combinationRule = new JsonSchemaCombinationRule<>(type, generators);
        }
    }

    @Override
    public JsonSchemaCombinationRule<G> getCombinationRule() {
        return combinationRule;
    }

    T getCombinationValue(@NotNull Supplier<T> defaultGenerator) {
        if (combinationRule == null) return defaultGenerator.get();

        List<G> generators = combinationRule.getGenerators();
        if (generators == null || generators.isEmpty()) return defaultGenerator.get();

        int index = RandomUtil.nextInt(generators.size());
        G randomGenerator = generators.get(index);

        return (randomGenerator == null) ? defaultGenerator.get() : randomGenerator.next();
    }

    private G getGenerator(JsonNode jsonNode) {
        P parser = parserFactory.getInstance(jsonNode);
        if (parser == null) return null;

        N node = parser.parse(jsonNode);
        return (node == null) ? null : node.getValueGenerator();
    }
}
