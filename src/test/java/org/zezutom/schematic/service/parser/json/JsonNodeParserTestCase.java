package org.zezutom.schematic.service.parser.json;

import org.zezutom.schematic.model.Node;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.fail;

/**
 * Base for all kinds of node tests.
 */
abstract class JsonNodeParserTestCase<T extends Node> {

    private static final String JSON_RESOURCE_PATH = "json" + File.separator + "%s" + File.separator + "%s";

    private final JsonSchemaParser parser = new JsonSchemaParser();

    T loadSchema(String dir, String fileName) {
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
