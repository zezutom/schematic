package org.zezutom.schematic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zezutom.schematic.model.json.Node;
import org.zezutom.schematic.model.json.schema.JsonDataType;
import org.zezutom.schematic.service.generator.json.StringGenerator;
import org.zezutom.schematic.service.parser.json.JsonNodeParser;
import org.zezutom.schematic.service.parser.json.JsonSchemaParser;
import org.zezutom.schematic.service.parser.json.node.JsonNodeParserFactory;

import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppIntegrationTest {

    @Autowired
    private JsonNodeParserFactory jsonNodeParserFactory;

    @Autowired
    private JsonSchemaParser jsonSchemaParser;

    @Autowired
    private StringGenerator stringGenerator;

    @Autowired
    private Node rootNode;

    @Test
    public void jsonNodeParserFactory() {
        assertNotNull(jsonNodeParserFactory);
        Map<JsonDataType, Class<? extends JsonNodeParser>> nodeParserMap = jsonNodeParserFactory.getNodeParserMap();
        assertNotNull(nodeParserMap);
        assertFalse(nodeParserMap.isEmpty());
    }

    @Test
    public void jsonSchemaParser() {
        assertNotNull(jsonSchemaParser);
        assertSame(jsonNodeParserFactory, jsonSchemaParser.getParserFactory());
    }

    @Test
    public void rootNode() {
        assertNotNull(rootNode);
        assertNotNull(rootNode.getValueGenerator());
        assertNotNull(rootNode.getValue());
    }

    @Test
    public void stringGenerator() {
        assertNotNull(stringGenerator);
        assertNotNull(stringGenerator.next());
    }
}
