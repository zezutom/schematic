package org.zezutom.schematic.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.zezutom.schematic.service.generator.EnumValueGenerator;
import org.zezutom.schematic.service.generator.NumberGeneratorToDelete;
import org.zezutom.schematic.service.generator.StringGeneratorToDelete;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static org.junit.Assert.*;

public class JsonUtilTest {

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void numberGeneratorIsNotNull() {
        assertNotNull(createNumberGenerator("{\"type\": \"number\"}"));
    }

    @Test
    public void numberGeneratorRespectsMinValue() {
        assertTrue(
        createNumberGenerator("{\"type\": \"number\", \"minimum\": 10}")
                .next()
                .intValue() > 10);
    }

    @Test
    public void numberGeneratorRespectsListOfValues() {
        List<Integer> expectedValues = Arrays.asList(1, 2, 3, 4, 5);
        String nodeDefinition = "[1, 2, 3, 4, 5]";
        assertTrue(expectedValues.contains(
                createNumberGenerator(nodeDefinition).next().intValue()));
    }

    @Test
    public void stringGeneratorIsNotNull() {
        assertNotNull(createNumberGenerator("{\"type\": \"string\"}"));
    }

    @Test
    public void stringGeneratorRespectsListOfValues() {
        List<String> expectedValues = Arrays.asList("one", "two", "three");
        String nodeDefinition = "[\"one\", \"two\", \"three\"]";
        assertTrue(expectedValues.contains(
                createStringGenerator(nodeDefinition).next()));
    }

    @Test
    public void stringGeneratorSupportsRegex() {
        String pattern = "abc-[0-9]+";
        String nodeDefinition = String.format("{\"type\":\"string\", \"pattern\":\"%s\"}", pattern);
        assertTrue(createStringGenerator(nodeDefinition).next().matches(pattern));
    }

    @Test
    public void recognisesArrayNode() {
        assertTrue(JsonUtil.isArray(parseJsonNode("[1, 2, 3]")));
    }

    @Test
    public void recognisesNumericNode() {
        assertTrue(JsonUtil.isNumber(parseJsonNode("{\"type\":\"number\"}")));
    }

    @Test
    public void recognisesBooleanNode() {
        assertTrue(JsonUtil.isBoolean(parseJsonNode("{\"type\":\"boolean\"}")));
    }

    private NumberGeneratorToDelete createNumberGenerator(String nodeDefinition) {
        return createGenerator(nodeDefinition, JsonUtil::createNumberGenerator);
    }

    private StringGeneratorToDelete createStringGenerator(String nodeDefinition) {
        return createGenerator(nodeDefinition, JsonUtil::createStringGenerator);
    }

    private<V, T extends EnumValueGenerator<V>> T createGenerator(String nodeDefinition, Function<JsonNode, T> factory) {
        T generator = factory.apply(parseJsonNode(nodeDefinition));
        assertNotNull(generator);
        return generator;
    }

    private JsonNode parseJsonNode(String definition) {
        try {
            return mapper.readTree(definition);
        } catch (IOException e) {
            fail(String.format("Couldn't parse node definition: '%s'", definition));
            return null;
        }
    }
}
