package parser.json;

import org.junit.Assert;
import org.junit.Test;
import org.zezutom.schematic.model.json.NumberNode;
import org.zezutom.schematic.model.json.schema.JsonSchemaCombinationRule;
import org.zezutom.schematic.model.json.schema.JsonSchemaCombinationType;
import org.zezutom.schematic.service.generator.json.NumberGenerator;

import java.util.List;

public class NumberParserIntegrationTest extends JsonNodeParserIntegrationTestCase<Number, NumberGenerator, NumberNode> {

    @Override
    String getResourceDir() {
        return "number";
    }

    @Test
    public void basic() {
        NumberGenerator generator = getGenerator("basic.json");
        Assert.assertFalse(generator.getExclusiveMaximum());
        Assert.assertFalse(generator.getExclusiveMinimum());
        Assert.assertNull(generator.getMaximum());
        Assert.assertNull(generator.getMinimum());
        Assert.assertNull(generator.getMultipleOf());
    }

    @Test
    public void multipleOf() {
        NumberGenerator generator = getGenerator("multiple_of.json");
        Assert.assertTrue(generator.getMultipleOf() == 10);
    }

    @Test
    public void oneOf() {
        NumberGenerator generator = getGenerator("one_of.json");
        assertCombinationRule(
                generator.getCombinationRule(),
                JsonSchemaCombinationType.ONE_OF,
                NumberGenerator::getMultipleOf, 3, 5);
    }

    @Test
    public void allOf() {
        NumberGenerator generator = getGenerator("all_of.json");
        JsonSchemaCombinationRule<NumberGenerator> rule = generator.getCombinationRule();
        assertCombinationRule(
                rule,
                JsonSchemaCombinationType.ALL_OF
        );
        List<NumberGenerator> generators = rule.getGenerators();
        Assert.assertEquals(2, generators.get(0).getMinimum());
        Assert.assertEquals(3, generators.get(1).getMaximum());
    }

    @Test
    public void range() {
        NumberGenerator generator = getGenerator("min_max.json");
        Assert.assertEquals(0, generator.getMinimum());
        Assert.assertEquals(100, generator.getMaximum());
        Assert.assertFalse(generator.getExclusiveMinimum());
        Assert.assertFalse(generator.getExclusiveMaximum());
    }

    @Test
    public void rangeExclusive() {
        NumberGenerator generator = getGenerator("min_max_exclusive.json");
        Assert.assertEquals(0, generator.getMinimum());
        Assert.assertEquals(100, generator.getMaximum());
        Assert.assertTrue(generator.getExclusiveMinimum());
        Assert.assertTrue(generator.getExclusiveMaximum());
    }
}
