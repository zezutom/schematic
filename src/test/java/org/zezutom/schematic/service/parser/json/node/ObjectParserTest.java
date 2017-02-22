package org.zezutom.schematic.service.parser.json.node;

import org.junit.Test;
import org.zezutom.schematic.TestUtil;
import org.zezutom.schematic.model.json.ObjectNode;
import org.zezutom.schematic.model.json.schema.JsonSchemaCombinationRule;
import org.zezutom.schematic.model.json.schema.JsonSchemaCombinationType;
import org.zezutom.schematic.service.generator.ValueGenerator;
import org.zezutom.schematic.service.generator.json.NumberGenerator;
import org.zezutom.schematic.service.generator.json.ObjectGenerator;
import org.zezutom.schematic.service.generator.json.StringGenerator;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

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
        assertProperty("street_type", StringGenerator.class);
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
        assertProperty("ip", StringGenerator.class);
        StringGenerator ipGenerator = (StringGenerator) generator.getProperty("ip");

        JsonSchemaCombinationRule<StringGenerator> combinationRule = ipGenerator.getCombinationRule();
        assertNotNull(combinationRule);
        assertTrue(JsonSchemaCombinationType.ONE_OF.equals(combinationRule.getType()));

        List<StringGenerator> generators = combinationRule.getGenerators();
        assertNotNull(generators);
        assertTrue(generators.size() == 2);
    }

    @Test
    public void parseAllOf() {
        parse("all_of.json");
        assertProperty("street_number", NumberGenerator.class);
    }

    private <T extends ValueGenerator>void assertProperty(String name, Class<T> expectedClass) {
        Map<String, ValueGenerator> propertiesMap = generator.getPropertiesMap();
        assertNotNull(propertiesMap);

        ValueGenerator generator = propertiesMap.get(name);
        assertNotNull(generator);
        assertTrue(expectedClass.equals(generator.getClass()));
    }
}
