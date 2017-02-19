package parser.json;

import org.junit.Test;
import org.zezutom.schematic.model.json.NullNode;
import org.zezutom.schematic.service.generator.json.NullGenerator;

import javax.lang.model.type.NullType;

public class NullParserIntegrationTest extends JsonNodeParserIntegrationTestCase<NullType, NullGenerator, NullNode> {

    @Override
    String getResourceDir() {
        return "null";
    }

    @Test
    public void basic() {
        // Leave with default tests
        getGenerator("basic.json");
    }

}
