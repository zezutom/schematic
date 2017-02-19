package parser.json;

import org.junit.Test;
import org.zezutom.schematic.model.json.BooleanNode;
import org.zezutom.schematic.service.generator.json.BooleanGenerator;

public class BooleanParserIntegrationTest extends JsonNodeParserIntegrationTestCase<Boolean, BooleanGenerator, BooleanNode> {

    @Override
    String getResourceDir() {
        return "boolean";
    }

    @Test
    public void basic() {
        // Leave with default tests
        getGenerator("basic.json");
    }

}