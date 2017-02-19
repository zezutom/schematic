package parser.json;

import org.junit.Assert;
import org.junit.Test;
import org.zezutom.schematic.model.json.EnumNode;
import org.zezutom.schematic.service.generator.json.EnumGenerator;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

public class EnumParserIntegrationTest extends JsonNodeParserIntegrationTestCase<Object, EnumGenerator, EnumNode> {

    @Override
    String getResourceDir() {
        return "enum";
    }

    @Test
    public void basic() {
        EnumGenerator generator = getGenerator("basic.json");
        assertItems(generator, "red", "amber", "green");
    }

    @Test
    public void differentTypes() {
        EnumGenerator generator = getGenerator("different_types.json");
        assertItems(generator, "red", "amber", "green", null, 42);
    }

    private void assertItems(@NotNull EnumGenerator generator, Object... expectedValues) {
        List<Object> items = generator.getItems();
        Assert.assertNotNull(items);
        Assert.assertTrue(items.containsAll(Arrays.asList(expectedValues)));
    }
}
