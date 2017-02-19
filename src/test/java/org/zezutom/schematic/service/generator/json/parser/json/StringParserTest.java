package org.zezutom.schematic.service.generator.json.parser.json;

import org.junit.Test;
import org.zezutom.schematic.model.json.StringNode;
import org.zezutom.schematic.model.json.schema.JsonStringFormat;
import org.zezutom.schematic.service.generator.json.StringGenerator;
import org.zezutom.schematic.service.parser.json.node.StringParser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class StringParserTest extends BaseJsonNodeParserTestCase<StringParser> {

    private StringGenerator generator;

    @Override
    StringParser newInstance() {
        generator = new StringGenerator();
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

    private void parseFormat(String fileName, JsonStringFormat expectedFormat) {
        StringNode node = parser.parse(getJsonNode(fileName));
        assertNotNull(node);
        assertEquals(expectedFormat, generator.getFormat());
    }
}
