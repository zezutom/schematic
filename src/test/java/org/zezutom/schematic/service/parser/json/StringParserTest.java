package org.zezutom.schematic.service.parser.json;

import org.junit.Test;
import org.zezutom.schematic.model.StringNode;
import org.zezutom.schematic.model.json.JsonSchemaCombinationRule;
import org.zezutom.schematic.model.json.JsonSchemaCombinationType;
import org.zezutom.schematic.service.generator.json.StringGenerator;

import java.util.List;

import static org.junit.Assert.*;

public class StringParserTest extends JsonNodeParserTestCase<String, StringGenerator, StringNode> {

    @Override
    String getResourceDir() {
        return "string";
    }

    @Test
    public void basic() {
        StringGenerator generator = getGenerator("basic.json");
        assertNull(generator.getFormat());
        assertNull(generator.getMaxLength());
        assertNull(generator.getMinLength());
        assertNull(generator.getPattern());
    }

    @Test
    public void pattern() {
        assertEquals("^(\\([0-9]{3}\\))?[0-9]{3}-[0-9]{4}$",
                getGenerator("pattern.json").getPattern());
    }

    @Test
    public void dateTime() {
        StringGenerator generator = getGenerator("date_time.json");
        assertEquals("date-time", generator.getFormat());
    }

    @Test
    public void email() {
        StringGenerator generator = getGenerator("email.json");
        assertEquals("email", generator.getFormat());
    }

    @Test
    public void hostname() {
        StringGenerator generator = getGenerator("hostname.json");
        assertEquals("hostname", generator.getFormat());
    }

    @Test
    public void ipv4() {
        StringGenerator generator = getGenerator("ipv4.json");
        assertEquals("ipv4", generator.getFormat());
    }

    @Test
    public void ipv6() {
        StringGenerator generator = getGenerator("ipv6.json");
        assertEquals("ipv6", generator.getFormat());
    }

    @Test
    public void length() {
        StringGenerator generator = getGenerator("min_max_length.json");
        assertTrue(generator.getMinLength() == 2);
        assertTrue(generator.getMaxLength() == 3);
    }

    @Test
    public void oneOf() {
        StringGenerator generator = getGenerator("one_of.json");
        assertCombinationRule(
                generator.getCombinationRule(),
                JsonSchemaCombinationType.ONE_OF,
                StringGenerator::getFormat,
                "ipv4", "ipv6");
    }

    @Test
    public void allOf() {
        StringGenerator generator = getGenerator("all_of.json");
        JsonSchemaCombinationRule<StringGenerator> rule = generator.getCombinationRule();
        assertCombinationRule(
                rule,
                JsonSchemaCombinationType.ALL_OF
        );
        List<StringGenerator> generators = rule.getGenerators();
        assertTrue(generators.get(0).getMinLength() == 2);
        assertTrue(generators.get(1).getMaxLength() == 3);
    }
}
