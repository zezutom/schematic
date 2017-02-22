package org.zezutom.schematic.service.generator.json;

import org.junit.Test;
import org.zezutom.schematic.TestUtil;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ObjectGeneratorTest extends ValueGeneratorTestCase<Map<String, Object>, ObjectGenerator> {

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

    private void assertProperty(Map<String, Object> properties, String key, Object expectedValue) {
        assertEquals(expectedValue, properties.get(key));
    }


}
