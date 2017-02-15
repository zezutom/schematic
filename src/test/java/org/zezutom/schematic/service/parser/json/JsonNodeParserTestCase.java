package org.zezutom.schematic.service.parser.json;

import org.zezutom.schematic.model.json.Node;
import org.zezutom.schematic.model.json.schema.JsonSchemaCombinationRule;
import org.zezutom.schematic.model.json.schema.JsonSchemaCombinationType;
import org.zezutom.schematic.service.generator.ValueGenerator;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.function.Function;

import static org.junit.Assert.*;

/**
 * Allows to test parsing of primitive data types.
 */
abstract class JsonNodeParserTestCase<T, G extends ValueGenerator<T>, N extends Node<T, G>> {

    private static final String JSON_RESOURCE_PATH = "json" + File.separator + "%s" + File.separator + "%s";

    private final JsonSchemaParser parser = new JsonSchemaParser();

    abstract String getResourceDir();

    @NotNull G getGenerator(String fileName) {
        return getGenerator(getResourceDir(), fileName);
    }

    <V, VG extends ValueGenerator<V>>void assertCombinationRule(
            JsonSchemaCombinationRule<VG> rule,
            JsonSchemaCombinationType expectedType
    ) {
        assertCombinationRule(rule, expectedType, null);
        List<VG> generators = rule.getGenerators();
        assertNotNull(generators);
        assertTrue(generators.size() == 2);
    }

    @SafeVarargs
    final <V, VG extends ValueGenerator>void assertCombinationRule(
            JsonSchemaCombinationRule<VG> rule,
            JsonSchemaCombinationType expectedType,
            Function<VG, V> valueExtractor,
            V... expectedValues) {

        assertNotNull(rule);
        assertTrue(expectedType.equals(rule.getType()));

        if (valueExtractor != null && expectedValues != null) {
            List<VG> generators = rule.getGenerators();
            assertNotNull(generators);
            assertTrue(generators.size() == expectedValues.length);

            int i = 0;
            for (VG generator : generators) {
                assertEquals(expectedValues[i++], valueExtractor.apply(generator));
            }
        }
    }

    private @NotNull G getGenerator(String dir, String fileName) {
        N node = loadSchema(dir, fileName);
        assertNotNull(node);

        G generator = node.getValueGenerator();
        assertNotNull(generator);

        return generator;
    }

    private N loadSchema(String dir, String fileName) {
        try {
            InputStream inputStream = this.getClass()
                    .getClassLoader()
                    .getResourceAsStream(String.format(JSON_RESOURCE_PATH, dir == null ? "" : dir, fileName));
            return parser.parse(inputStream);
        } catch (IOException e) {
            fail(String.format("Unexpected exception: %s", e));
            return null;
        }
    }
}
