package org.zezutom.schematic.service.generator.json.parser.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.zezutom.schematic.TestUtil;
import org.zezutom.schematic.service.parser.json.JsonNodeParser;

import java.io.IOException;

import static org.junit.Assert.fail;

public abstract class BaseJsonNodeParserTestCase<T extends JsonNodeParser> {

    T parser;

    abstract T newInstance();

    abstract String getResourceDir();

    @Before
    public void before() {
        parser = newInstance();
    }

    @Test
    public void parse()  {
        // TODO
    }

    JsonNode getJsonNode(String fileName) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readTree(TestUtil.getResource(getResourceDir(), fileName));
        } catch (IOException e) {
            fail("Failed to parse JSON from file: " + e.getMessage());
        }
        return null;
    }
}
