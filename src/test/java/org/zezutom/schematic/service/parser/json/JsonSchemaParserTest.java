package org.zezutom.schematic.service.parser.json;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.zezutom.schematic.App;
import org.zezutom.schematic.TestUtil;
import org.zezutom.schematic.model.json.StringNode;
import org.zezutom.schematic.service.DataService;
import org.zezutom.schematic.service.generator.json.StringGenerator;
import org.zezutom.schematic.service.parser.json.node.JsonNodeParserFactory;
import org.zezutom.schematic.service.parser.json.node.StringParser;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JsonSchemaParserTest {

    private static final String RESOURCE_DIR = "string";

    private static final String RESOURCE_FILE = "basic.json";

    private JsonSchemaParser parser;

    @Before
    public void before() {
        StringParser stringParser = new StringParser(new StringGenerator(TestUtil.mockParserFactory(), mock(DataService.class)));

        ApplicationContext mockContext = mock(ApplicationContext.class);
        when(mockContext.getBean(StringParser.class)).thenReturn(stringParser);

        JsonNodeParserFactory parserFactory = App.jsonNodeParserFactory();
        parserFactory.setContext(mockContext);

        parser = new JsonSchemaParser(parserFactory);
    }

    @Test
    public void parseInputStream() throws IOException {
        assertNode(parser.parse(TestUtil.getResourceAsStream(RESOURCE_DIR, RESOURCE_FILE)));
    }

    @Test
    public void parseFilePath() throws IOException {
        assertNode(parser.parse(TestUtil.getResourcePath(RESOURCE_DIR, RESOURCE_FILE)));
    }

    @Test
    public void parseUnsupportedJsonNode() {
        JsonNode jsonNode = mock(JsonNode.class);
        when(jsonNode.has("type")).thenReturn(false);
        assertNull(parser.parse(jsonNode));
    }

    private void assertNode(StringNode node) {
        assertNotNull(node);
        assertNotNull(node.getValueGenerator());
    }
}
