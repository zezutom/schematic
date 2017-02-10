package org.zezutom.schematic.service.parser.json;

import org.zezutom.schematic.model.LeafNode;
import org.zezutom.schematic.service.generator.value.ValueGenerator;

import javax.validation.constraints.NotNull;

import static org.junit.Assert.assertNotNull;

/**
 * Allows to test parsing of primitive data types.
 */
abstract class JsonLeafNodeParserTestCase<T, G extends ValueGenerator<T>, N extends LeafNode<T, G>> extends JsonNodeParserTestCase<N> {

    abstract String getResourceDir();

    @NotNull G getGenerator(String fileName) {
        return getGenerator(getResourceDir(), fileName);
    }

    private @NotNull G getGenerator(String dir, String fileName) {
        N node = loadSchema(dir, fileName);
        assertNotNull(node);

        G generator = node.getValueGenerator();
        assertNotNull(generator);

        return generator;
    }
}
