package org.zezutom.schematic.service.generator.json.parser.json.node;

import org.junit.Test;
import org.zezutom.schematic.model.json.StringNode;
import org.zezutom.schematic.model.json.schema.JsonSchemaCombinationType;
import org.zezutom.schematic.model.json.schema.JsonStringFormat;
import org.zezutom.schematic.service.generator.json.StringGenerator;
import org.zezutom.schematic.service.parser.json.node.StringParser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StringParserTest extends BaseJsonNodeParserTestCase<String, StringGenerator, StringNode, StringParser> {

    @Override
    StringGenerator createGenerator() {
        return new StringGenerator();
    }

    @Override
    StringParser createParser(StringGenerator generator) {
        return new StringParser(generator);
    }

    @Override
    String getResourceDir() {
        return "string";
    }

    @Test
    public void parseIPv4()  {
        parseFormat("ipv4.json", JsonStringFormat.IPV4);
    }

    @Test
    public void parseIPv6()  {
        parseFormat("ipv6.json", JsonStringFormat.IPV6);
    }

    @Test
    public void parseDateTime()  {
        parseFormat("date_time.json", JsonStringFormat.DATE_TIME);
    }

    @Test
    public void parseEmail()  {
        parseFormat("email.json", JsonStringFormat.EMAIL);
    }

    @Test
    public void parseHostname()  {
        parseFormat("hostname.json", JsonStringFormat.HOSTNAME);
    }

    @Test
    public void parseLength() {
        parse("min_max_length.json");
        assertTrue(generator.getMinLength() == 2);
        assertTrue(generator.getMaxLength() == 3);
    }

    @Test
    public void parseOneOf() {
        parse("one_of.json");
        assertCombinationRule(generator.getCombinationRule(), JsonSchemaCombinationType.ONE_OF);
    }

    @Test
    public void parseAllOf() {
        parse("all_of.json");
        assertCombinationRule(generator.getCombinationRule(), JsonSchemaCombinationType.ALL_OF);
    }

    @Test
    public void parsePattern() {
        parse("pattern.json");
        assertEquals("^(\\([0-9]{3}\\))?[0-9]{3}-[0-9]{4}$", generator.getPattern());
    }

    private void parseFormat(String fileName, JsonStringFormat expectedFormat) {
        parse(fileName);
        assertEquals(expectedFormat, generator.getFormat());
    }

}
