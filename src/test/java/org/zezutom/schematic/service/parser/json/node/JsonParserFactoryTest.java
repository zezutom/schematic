package org.zezutom.schematic.service.parser.json.node;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Before;
import org.junit.Test;
import org.zezutom.schematic.App;
import org.zezutom.schematic.model.json.schema.JsonDataType;
import org.zezutom.schematic.model.json.schema.JsonSchemaAttribute;

import java.util.Collections;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JsonParserFactoryTest {

    private JsonNodeParserFactory factory;

    @Before
    public void before() {
        factory = App.jsonNodeParserFactory();
    }

    @Test
    public void getInstanceOnNullInputReturnsNull() {
        assertNull(factory.getInstance(null));
    }

    @Test
    public void getInstanceOnUnsupportedDataTypeReturnsNull() {
        JsonNode jsonNode = mock(JsonNode.class);
        assertNull(factory.getInstance(jsonNode));
    }

    @Test
    public void getInstanceOnDataTypeNotFoundReturnsNull() {
        JsonNode jsonNode = mock(JsonNode.class);
        String typeFieldName = JsonSchemaAttribute.TYPE.getValue();
        when(jsonNode.has(typeFieldName)).thenReturn(true);

        JsonNode typeNode = mock(JsonNode.class);
        when(typeNode.textValue()).thenReturn(JsonDataType.BOOLEAN.getValue());
        when(jsonNode.get(typeFieldName)).thenReturn(typeNode);

        assertNull(new JsonNodeParserFactory(Collections.emptyMap()).getInstance(jsonNode));
    }


}
