package org.zezutom.schematic.service.parser.json;

import org.junit.Test;
import org.zezutom.schematic.model.LeafNode;
import org.zezutom.schematic.model.Node;
import org.zezutom.schematic.service.generator.value.BooleanGenerator;
import org.zezutom.schematic.service.generator.value.NumberGenerator;
import org.zezutom.schematic.service.generator.value.StringGenerator;
import org.zezutom.schematic.service.generator.value.ValueGenerator;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;

public class JsonSchemaParserTest {

    private static final String JSON_RESOURCE_PATH = "json" + File.separator + "%s" + File.separator + "%s";

    private static final String FORMAT_RESOURCE_PATH = "format" + File.separator + "%s";

    private static final String STRING_RESOURCE_DIR = "string";

    private static final String NUMBER_RESOURCE_DIR = "number";

    private final JsonSchemaParser parser = new JsonSchemaParser();

    @Test
    public void string() {
       StringGenerator generator = getStringGenerator("basic.json");
       assertNull(generator.getFormat());
       assertNull(generator.getMaxLength());
       assertNull(generator.getMinLength());
       assertNull(generator.getPattern());
    }

    @Test
    public void stringPattern() {
        assertEquals("^(\\([0-9]{3}\\))?[0-9]{3}-[0-9]{4}$",
                getStringGenerator("pattern.json").getPattern());
    }

    @Test
    public void stringFormatDateTime() {
        StringGenerator generator = getStringFormatGenerator("date_time.json");
        assertEquals("date-time", generator.getFormat());
    }

    @Test
    public void stringFormatEmail() {
        StringGenerator generator = getStringFormatGenerator("email.json");
        assertEquals("email", generator.getFormat());
    }

    @Test
    public void stringFormatHostname() {
        StringGenerator generator = getStringFormatGenerator("hostname.json");
        assertEquals("hostname", generator.getFormat());
    }

    @Test
    public void stringFormatIPv4() {
        StringGenerator generator = getStringFormatGenerator("ipv4.json");
        assertEquals("ipv4", generator.getFormat());
    }

    @Test
    public void stringFormatIPv6() {
        StringGenerator generator = getStringFormatGenerator("ipv6.json");
        assertEquals("ipv6", generator.getFormat());
    }

    @Test
    public void stringLength() {
        StringGenerator generator = getStringGenerator("min_max_length.json");
        assertTrue(generator.getMinLength() == 2);
        assertTrue(generator.getMaxLength() == 3);
    }

    @Test
    public void number() {
        NumberGenerator generator = getNumberGenerator("basic.json");
        assertFalse(generator.getExclusiveMaximum());
        assertFalse(generator.getExclusiveMinimum());
        assertNull(generator.getMaximum());
        assertNull(generator.getMinimum());
        assertNull(generator.getMultipleOf());
    }

    @Test
    public void numberMultipleOf() {
        NumberGenerator generator = getNumberGenerator("multiple_of.json");
        assertTrue(generator.getMultipleOf() == 10);
    }

    @Test
    public void numberRange() {
        NumberGenerator generator = getNumberGenerator("min_max.json");
        assertEquals(0, generator.getMinimum());
        assertEquals(100, generator.getMaximum());
        assertFalse(generator.getExclusiveMinimum());
        assertFalse(generator.getExclusiveMaximum());
    }

    @Test
    public void numberRangeExclusive() {
        NumberGenerator generator = getNumberGenerator("min_max_exclusive.json");
        assertEquals(0, generator.getMinimum());
        assertEquals(100, generator.getMaximum());
        assertTrue(generator.getExclusiveMinimum());
        assertTrue(generator.getExclusiveMaximum());
    }

    @Test
    public void bool() {
        getValueGenerator("", "boolean.json", BooleanGenerator.class);
    }

    private StringGenerator getStringGenerator(String fileName) {
        return getValueGenerator(STRING_RESOURCE_DIR, fileName, StringGenerator.class);
    }

    private StringGenerator getStringFormatGenerator(String fileName) {
        return getValueGenerator(STRING_RESOURCE_DIR, String.format(FORMAT_RESOURCE_PATH, fileName), StringGenerator.class);
    }

    private NumberGenerator getNumberGenerator(String fileName) {
        return getValueGenerator(NUMBER_RESOURCE_DIR, fileName, NumberGenerator.class);
    }

    private <V, T extends ValueGenerator<V>> T getValueGenerator(String dir, String fileName, Class<T> generatorClass) {
        Node node = loadSchema(dir, fileName);
        verifyAsLeafNode(node, generatorClass);
        return (T) ((LeafNode<V>) node).getValueGenerator();
    }

    private<V, T extends ValueGenerator<V>> void verifyAsLeafNode(Node node, Class<T> generatorClass) {
        assertNotNull(node);
        assertTrue(node instanceof LeafNode);
        ValueGenerator valueGenerator = ((LeafNode) node).getValueGenerator();
        assertNotNull(valueGenerator);
        assertTrue(generatorClass.equals(valueGenerator.getClass()));
    }

    private Node loadSchema(String dir, String fileName) {
        try {
            InputStream inputStream = this.getClass()
                    .getClassLoader()
                    .getResourceAsStream(String.format(JSON_RESOURCE_PATH, dir, fileName));
            return parser.parse(inputStream);
        } catch (IOException e) {
            fail(String.format("Unexpected exception: %s", e));
            return null;
        }
    }
}
