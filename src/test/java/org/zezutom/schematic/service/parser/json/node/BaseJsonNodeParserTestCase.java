package org.zezutom.schematic.service.parser.json.node;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.zezutom.schematic.TestUtil;
import org.zezutom.schematic.model.json.Node;
import org.zezutom.schematic.model.json.schema.JsonSchemaCombinationRule;
import org.zezutom.schematic.model.json.schema.JsonSchemaCombinationType;
import org.zezutom.schematic.service.generator.ValueGenerator;
import org.zezutom.schematic.service.parser.json.JsonNodeParser;

import java.io.IOException;

import static org.junit.Assert.*;

public abstract class BaseJsonNodeParserTestCase<T, G extends ValueGenerator<T>, N extends Node<T, G>, P extends JsonNodeParser<N>> {

    G generator;

    P parser;

    abstract G createGenerator();

    abstract P createParser(G generator);

    abstract String getResourceDir();

    @Before
    public void before() {
        generator = createGenerator();
        parser = createParser(generator);
    }

    @Test
    public void parseNullNodeReturnsNull() {
        assertNull(parser.parse(null));
    }

    void parse(String fileName) {
        N node = parser.parse(getJsonNode(fileName));
        assertNotNull(node);
    }

    void assertCombinationRule(JsonSchemaCombinationRule rule, JsonSchemaCombinationType expectedType) {
        assertNotNull(rule);
        assertEquals(expectedType, rule.getType());
    }

    private JsonNode getJsonNode(String fileName) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readTree(TestUtil.getResourceAsStream(getResourceDir(), fileName));
        } catch (IOException e) {
            fail("Failed to parse JSON from file: " + e.getMessage());
        }
        return null;
    }
}
