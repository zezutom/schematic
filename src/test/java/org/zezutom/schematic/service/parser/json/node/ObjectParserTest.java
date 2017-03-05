package org.zezutom.schematic.service.parser.json.node;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Test;
import org.zezutom.schematic.TestUtil;
import org.zezutom.schematic.model.json.ObjectNode;
import org.zezutom.schematic.model.json.schema.JsonSchemaCombinationRule;
import org.zezutom.schematic.model.json.schema.JsonSchemaCombinationType;
import org.zezutom.schematic.model.json.schema.JsonStringFormat;
import org.zezutom.schematic.service.generator.ValueGenerator;
import org.zezutom.schematic.service.generator.json.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class ObjectParserTest extends BaseJsonNodeParserTestCase<Map<String, Object>, ObjectGenerator, ObjectNode, ObjectParser> {

    private final JsonNodeParserFactory parserFactory = TestUtil.mockParserFactory();

    @Override
    ObjectGenerator createGenerator() {
        return new ObjectGenerator(TestUtil.mockParserFactory());
    }

    @Override
    ObjectParser createParser(ObjectGenerator generator) {
        return new ObjectParser(generator, parserFactory);
    }

    @Override
    String getResourceDir() {
        return "object";
    }

    @Test
    public void parse() {
        parse("basic.json");
    }

    @Test
    public void parseProperties() {
        parse("properties.json");
        assertProperty("number", NumberGenerator.class);
        assertProperty("street_name", StringGenerator.class);
        assertProperty("street_type", EnumGenerator.class);
    }

    @Test
    public void parseDisableAdditionalProperties() {
        parse("additional_properties.json");
        assertFalse(generator.isAdditionalPropertiesAllowed());
    }

    @Test
    public void parseAdditionalPropertiesAsSchema() {
        parse("additional_properties_as_schema.json");
        Set<ValueGenerator> additionalGenerators = generator.getAdditionalGenerators();
        assertNotNull(additionalGenerators);
        assertTrue(additionalGenerators.stream().anyMatch(x -> x instanceof StringGenerator));
    }

    @Test
    public void parseRequiredProperties() {
        parse("required.json");
        Set<String> requiredProperties = generator.getRequiredProperties();
        assertNotNull(requiredProperties);
        assertTrue(requiredProperties.containsAll(Arrays.asList("name", "email")));
    }

    @Test
    public void parseLength() {
        parse("min_max_properties.json");
        assertTrue(generator.getMin() == 2);
        assertTrue(generator.getMax() == 3);
    }

    @Test
    public void parseOneOf() {
        parse("one_of.json");

        JsonSchemaCombinationRule<StringGenerator> combinationRule = getCombinationRule(
                "one_of.json", "ip", StringGenerator.class, JsonSchemaCombinationType.ONE_OF);

        List<StringGenerator> generators = combinationRule.getGenerators();
        assertNotNull(generators);
        assertTrue(generators.size() == 2);
        assertTrue(JsonStringFormat.IPV4.equals(generators.get(0).getFormat()));
        assertTrue(JsonStringFormat.IPV6.equals(generators.get(1).getFormat()));
    }

    @Test
    public void parseAllOf() {
        JsonSchemaCombinationRule<NumberGenerator> combinationRule = getCombinationRule(
                "all_of.json", "street_number", NumberGenerator.class, JsonSchemaCombinationType.ALL_OF);
        List<NumberGenerator> generators = combinationRule.getGenerators();
        assertNotNull(generators);
        assertTrue(generators.size() == 2);

        Number min = generators.get(0).getMinimum();
        assertNotNull(min);
        assertTrue(min.intValue() == 1);

        Number max = generators.get(1).getMaximum();
        assertNotNull(max);
        assertTrue(max.intValue() == 999);
    }

    @Test
    public void resolveGeneratorOnNullInputReturnsNull() {
        assertNull(parser.resolveGenerator(null));
    }

    @Test
    public void resolveGeneratorOnUnsupportedTypeReturnsNull() {
        JsonNode jsonNode = mock(JsonNode.class);
        assertNull(parser.resolveGenerator(jsonNode));
    }

    private <T extends JsonSchemaGenerator> JsonSchemaCombinationRule<T> getCombinationRule(
            String fileName, String propertyName, Class<T> generatorClass, JsonSchemaCombinationType expectedType) {
        parse(fileName);
        assertProperty(propertyName, generatorClass);
        T propertyGenerator = (T) generator.getProperty(propertyName);
        assertNotNull(propertyGenerator);
        assertProperty(propertyName, generatorClass);

        JsonSchemaCombinationRule<T> combinationRule = propertyGenerator.getCombinationRule();
        assertNotNull(combinationRule);
        assertTrue(expectedType.equals(combinationRule.getType()));

        return combinationRule;
    }

    private <T extends ValueGenerator>void assertProperty(String name, Class<T> expectedClass) {
        Map<String, ValueGenerator> propertiesMap = generator.getPropertiesMap();
        assertNotNull(propertiesMap);

        ValueGenerator generator = propertiesMap.get(name);
        assertNotNull(generator);
        assertTrue(expectedClass.equals(generator.getClass()));
    }
}
