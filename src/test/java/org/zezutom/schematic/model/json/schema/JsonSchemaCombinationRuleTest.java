package org.zezutom.schematic.model.json.schema;

import org.junit.Before;
import org.junit.Test;
import org.zezutom.schematic.service.generator.json.StringGenerator;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;

public class JsonSchemaCombinationRuleTest {

    private JsonSchemaCombinationRule<StringGenerator> rule;

    private final JsonSchemaCombinationType type = JsonSchemaCombinationType.ALL_OF;

    private final List<StringGenerator> generators = Collections.singletonList(mock(StringGenerator.class));

    @Before
    public void before() {
        rule = new JsonSchemaCombinationRule<>(type, generators);
    }

    @Test
    public void constructor() {
       assertEquals(type, rule.getType());
       assertEquals(generators, rule.getGenerators());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void generatorsAreImmutable() {
        rule.getGenerators().add(mock(StringGenerator.class));
        fail("It shouldn't be possible to tamper with generators");
    }
}
