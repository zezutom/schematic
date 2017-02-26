package org.zezutom.schematic.service.generator.json;

import org.junit.Test;
import org.zezutom.schematic.TestUtil;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class ObjectGeneratorTest extends SchemaGeneratorTestCase<Map<String, Object>, ObjectGenerator> {

    @Override
    ObjectGenerator newInstance() {
        return new ObjectGenerator(TestUtil.mockParserFactory());
    }

    @Test
    public void next() {
        String stringPropertyKey = "a_string";
        String numberPropertyKey = "a_number";
        String stringPropertyValue = "test";
        int numberPropertyValue = 10;

        generator.addProperty(stringPropertyKey, TestUtil.mockGenerator(StringGenerator.class, stringPropertyValue));
        generator.addProperty(numberPropertyKey, TestUtil.mockGenerator(NumberGenerator.class, numberPropertyValue));

        Map<String, Object> properties = generator.next();
        assertNotNull(properties);
        assertProperty(properties, stringPropertyKey, stringPropertyValue);
        assertProperty(properties, numberPropertyKey, numberPropertyValue);
    }

    @Test
    public void addProperty() {
        String key = "test";
        StringGenerator stringGenerator = mock(StringGenerator.class);
        generator.addProperty(key, stringGenerator);
        assertEquals(stringGenerator, generator.getPropertiesMap().get(key));
    }

    @Test
    public void addPropertyNullKeyIsNotAdded() {
        generator.addProperty(null, mock(StringGenerator.class));
        assertTrue(generator.getPropertiesMap().isEmpty());
    }

    @Test
    public void addPropertyEmptyKeyIsNotAdded() {
        generator.addProperty("", mock(StringGenerator.class));
        assertTrue(generator.getPropertiesMap().isEmpty());
    }

    @Test
    public void addPropertyNullGeneratorIsNotAdded() {
        generator.addProperty("test", null);
        assertTrue(generator.getPropertiesMap().isEmpty());
    }

    @Test
    public void addPropertyInvalidEntryIsNotAdded() {
        generator.addProperty("", null);
        assertTrue(generator.getPropertiesMap().isEmpty());
    }

    @Test
    public void addAdditionalGenerator() {
        StringGenerator stringGenerator = mock(StringGenerator.class);
        generator.addAdditionalGenerator(stringGenerator);
        assertTrue(generator.getAdditionalGenerators().contains(stringGenerator));
    }

    @Test
    public void addAdditionalGeneratorNullInputIsNotAdded() {
        generator.addAdditionalGenerator(null);
        assertTrue(generator.getAdditionalGenerators().isEmpty());
    }

    @Test
    public void addAdditionalGeneratorOnDisabledGeneratorIsNotAdded() {
        generator.setAdditionalPropertiesAllowed(false);
        generator.addAdditionalGenerator(mock(StringGenerator.class));
        assertTrue(generator.getAdditionalGenerators().isEmpty());
    }

    @Test
    public void addRequiredProperty() {
        String property = "test";
        generator.addRequiredProperty(property);
        assertTrue(generator.getRequiredProperties().contains(property));
    }

    @Test
    public void addRequiredPropertyNullIsNotAdded() {
        generator.addRequiredProperty(null);
        assertTrue(generator.getRequiredProperties().isEmpty());
    }

    @Test
    public void addRequiredPropertyEmptyIsNotAdded() {
        generator.addRequiredProperty("");
        assertTrue(generator.getRequiredProperties().isEmpty());
    }

    private void assertProperty(Map<String, Object> properties, String key, Object expectedValue) {
        assertEquals(expectedValue, properties.get(key));
    }


}
