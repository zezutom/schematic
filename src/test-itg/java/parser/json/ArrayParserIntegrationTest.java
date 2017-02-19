package parser.json;

import org.junit.Assert;
import org.junit.Test;
import org.zezutom.schematic.model.json.ArrayNode;
import org.zezutom.schematic.service.generator.ValueGenerator;
import org.zezutom.schematic.service.generator.json.ArrayGenerator;
import org.zezutom.schematic.service.generator.json.NumberGenerator;
import org.zezutom.schematic.service.generator.json.StringGenerator;

import java.util.List;

public class ArrayParserIntegrationTest extends JsonNodeParserIntegrationTestCase<List<Object>, ArrayGenerator, ArrayNode> {

    @Override
    String getResourceDir() {
        return "array";
    }

    @Test
    public void basic() {
        Assert.assertTrue(getItems("basic.json").isEmpty());
    }

    @Test
    public void items() {
        Assert.assertTrue(getItems("items.json")
                .stream()
                .filter(x -> x instanceof NumberGenerator)
                .count() == 1);
    }

    @Test
    public void tuple() {
        List<ValueGenerator> generators = getItems("tuple.json");
        Assert.assertTrue(generators
                .stream()
                .filter(x -> x instanceof NumberGenerator)
                .count() == 1);
        Assert.assertTrue(generators
                .stream()
                .filter(x -> x instanceof StringGenerator)
                .count() == 3);

    }

    @Test
    public void additionalItems() {
        ArrayGenerator generator = getGenerator("additional_items.json");
        Assert.assertTrue(generator.getAdditionalItems());
    }

    @Test
    public void uniqueItems() {
        ArrayGenerator generator = getGenerator("unique_items.json");
        Assert.assertTrue(generator.getUniqueItems());
    }

    @Test
    public void minMaxItems() {
        ArrayGenerator generator = getGenerator("min_max_items.json");
        Assert.assertTrue(generator.getMinItems() == 2);
        Assert.assertTrue(generator.getMaxItems() == 3);
    }

    private List<ValueGenerator> getItems(String fileName) {
        ArrayGenerator generator = getGenerator(fileName);
        List<ValueGenerator> items = generator.getItems();
        Assert.assertNotNull(items);
        return items;
    }
}
