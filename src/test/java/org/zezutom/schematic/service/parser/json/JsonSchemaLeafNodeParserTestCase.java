package org.zezutom.schematic.service.parser.json;

import org.zezutom.schematic.model.LeafNode;
import org.zezutom.schematic.model.Node;
import org.zezutom.schematic.service.generator.value.ValueGenerator;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;

/**
 * Allows to test parsing of primitive data types.
 */
abstract class JsonSchemaLeafNodeParserTestCase<T, G extends ValueGenerator<T>> {

    private static final String JSON_RESOURCE_PATH = "json" + File.separator + "%s" + File.separator + "%s";

    private final JsonSchemaParser parser = new JsonSchemaParser();

    abstract String getResourceDir();

    @NotNull G getGenerator(String fileName) {
        return getGenerator(getResourceDir(), fileName);
    }

    private @NotNull G getGenerator(String dir, String fileName) {
        Node node = loadSchema(dir, fileName);
        assertNotNull(node);
        assertTrue(LeafNode.class.equals(node.getClass()));

        G generator = (G) ((LeafNode<T>) node).getValueGenerator();
        assertNotNull(generator);

        return generator;
    }

    private Node loadSchema(String dir, String fileName) {
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
