package parser.json;

import org.junit.Assert;
import org.junit.Test;
import org.zezutom.schematic.model.json.IntegerNode;
import org.zezutom.schematic.service.generator.json.IntegerGenerator;

public class IntegerParserIntegrationTest extends JsonNodeParserIntegrationTestCase<Integer, IntegerGenerator, IntegerNode> {

    @Override
    String getResourceDir() {
        return "integer";
    }

    @Test
    public void basic() {
        IntegerGenerator generator = getGenerator("basic.json");
        Assert.assertNull(generator.getMultipleOf());
    }

    @Test
    public void multipleOf() {
        IntegerGenerator generator = getGenerator("multiple_of.json");
        Assert.assertTrue(generator.getMultipleOf() == 10);
    }
}
