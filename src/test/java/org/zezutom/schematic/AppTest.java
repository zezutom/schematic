package org.zezutom.schematic;

import org.junit.Before;
import org.junit.Test;
import org.zezutom.schematic.model.json.schema.JsonDataType;
import org.zezutom.schematic.service.generator.json.StringGenerator;
import org.zezutom.schematic.service.parser.json.JsonNodeParser;
import org.zezutom.schematic.service.parser.json.JsonSchemaParser;
import org.zezutom.schematic.service.parser.json.node.*;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;

public class AppTest {

    private App app;

    @Before
    public void before() {
        app = new App();
    }

    @Test
    public void jsonNodeParserFactoryIsFullyInitialised() {
        JsonNodeParserFactory factory = App.jsonNodeParserFactory();
        assertNotNull(factory);
        Map<JsonDataType, Class<? extends JsonNodeParser>> nodeParserMap = factory.getNodeParserMap();
        assertNotNull(nodeParserMap);

        assertEquals(ArrayParser.class,  nodeParserMap.get(JsonDataType.ARRAY));
        assertEquals(BooleanParser.class,  nodeParserMap.get(JsonDataType.BOOLEAN));
        assertEquals(EnumParser.class,  nodeParserMap.get(JsonDataType.ENUM));
        assertEquals(IntegerParser.class,  nodeParserMap.get(JsonDataType.INTEGER));
        assertEquals(NullParser.class,  nodeParserMap.get(JsonDataType.NULL));
        assertEquals(NumberParser.class,  nodeParserMap.get(JsonDataType.NUMBER));
        assertEquals(ObjectParser.class,  nodeParserMap.get(JsonDataType.OBJECT));
        assertEquals(StringParser.class,  nodeParserMap.get(JsonDataType.STRING));
    }

    @Test
    public void jsonSchemaParserIsFullyInitialised() {
        JsonNodeParserFactory factory = mock(JsonNodeParserFactory.class);
        JsonSchemaParser parser = App.jsonSchemaParser(factory);
        assertNotNull(parser);
        assertSame(factory, parser.getParserFactory());
    }

    @Test
    public void stringGeneratorIsFullyInitialised() {
        JsonNodeParserFactory factory = mock(JsonNodeParserFactory.class);
        StringGenerator generator = app.stringGenerator(factory);
        assertSame(factory, generator.getParserFactory());
    }
}
