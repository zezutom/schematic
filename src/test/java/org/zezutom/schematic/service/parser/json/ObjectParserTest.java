package org.zezutom.schematic.service.parser.json;

import org.junit.Test;
import org.zezutom.schematic.model.json.ObjectNode;
import org.zezutom.schematic.model.json.schema.JsonSchemaCombinationRule;
import org.zezutom.schematic.model.json.schema.JsonSchemaCombinationType;
import org.zezutom.schematic.model.json.schema.JsonStringFormat;
import org.zezutom.schematic.service.generator.ValueGenerator;
import org.zezutom.schematic.service.generator.json.NumberGenerator;
import org.zezutom.schematic.service.generator.json.ObjectGenerator;
import org.zezutom.schematic.service.generator.json.StringGenerator;

import javax.validation.constraints.NotNull;
import java.util.*;

import static org.junit.Assert.*;

public class ObjectParserTest extends JsonNodeParserTestCase<Object, ObjectGenerator, ObjectNode> {

    @Override
    String getResourceDir() {
        return "object";
    }

    @Test
    public void basic() {
        ObjectGenerator generator = getGenerator("basic.json");
        Map<String, ValueGenerator> properties = generator.getPropertiesMap();
        assertNotNull(properties);
        assertTrue(properties.isEmpty());
    }

    @Test
    public void properties() {
        ObjectGenerator generator = getGenerator("properties.json");
        Map<String, ValueGenerator> properties = generator.getPropertiesMap();
        assertNotNull(properties);
        assertProperty(properties, "number", NumberGenerator.class);
        assertProperty(properties, "street_name", StringGenerator.class);
        assertProperty(properties, "street_type", StringGenerator.class);
    }

    @Test
    public void minMaxProperties() {
        ObjectGenerator generator = getGenerator("min_max_properties.json");
        assertTrue(generator.getMin() == 2);
        assertTrue(generator.getMax() == 3);
    }

    @Test
    public void additionalProperties() {
        ObjectGenerator generator = getGenerator("additional_properties.json");
        assertFalse(generator.isAdditionalPropertiesAllowed());
    }

    @Test
    public void additionalPropertiesAsSchema() {
        ObjectGenerator generator = getGenerator("additional_properties_as_schema.json");
        Set<ValueGenerator> additionalGenerators = generator.getAdditionalGenerators();
        assertNotNull(additionalGenerators);
        assertTrue(additionalGenerators.size() == 1);

        Optional<ValueGenerator> valueGeneratorOptional = additionalGenerators.stream().findFirst();
        if (valueGeneratorOptional.isPresent()) {
            assertTrue(valueGeneratorOptional.get() instanceof StringGenerator);
        } else {
            fail("A String generator was expected!");
        }
    }

    @Test
    public void required() {
        ObjectGenerator generator = getGenerator("required.json");
        Set<String> requiredProperties = generator.getRequiredProperties();
        assertNotNull(requiredProperties);
        assertTrue(requiredProperties.size() == 2);
        assertTrue(requiredProperties.containsAll(Arrays.asList("name", "email")));

    }

    @Test
    public void oneOf() {
        ObjectGenerator generator = getGenerator("one_of.json");
        ValueGenerator propertyGenerator = generator.getProperty("ip");
        assertNotNull(propertyGenerator);
        assertTrue(propertyGenerator instanceof StringGenerator);

        StringGenerator ipGenerator = (StringGenerator) propertyGenerator;
        assertCombinationRule(
                ipGenerator.getCombinationRule(),
                JsonSchemaCombinationType.ONE_OF,
                StringGenerator::getFormat,
                JsonStringFormat.IPV4, JsonStringFormat.IPV6);
    }

    @Test
    public void allOf() {
        ObjectGenerator generator = getGenerator("all_of.json");
        ValueGenerator propertyGenerator = generator.getProperty("street_number");
        assertNotNull(propertyGenerator);
        assertTrue(propertyGenerator instanceof NumberGenerator);

        NumberGenerator streetNumberGenerator = (NumberGenerator) propertyGenerator;
        JsonSchemaCombinationRule<NumberGenerator> rule = streetNumberGenerator.getCombinationRule();
        assertCombinationRule(
                rule,
                JsonSchemaCombinationType.ALL_OF
        );
        List<NumberGenerator> generators = rule.getGenerators();
        assertEquals(1, generators.get(0).getMinimum());
        assertEquals(999, generators.get(1).getMaximum());
    }

    private<T extends ValueGenerator> void assertProperty(@NotNull Map<String, ValueGenerator> properties, String name, Class<T> expectedGeneratorClass) {
        ValueGenerator generator = properties.get(name);
        assertNotNull(generator);
        assertTrue(expectedGeneratorClass.equals(generator.getClass()));
    }
}
