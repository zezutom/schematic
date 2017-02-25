package org.zezutom.schematic.util;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Test;
import org.zezutom.schematic.model.json.schema.JsonDataType;
import org.zezutom.schematic.model.json.schema.JsonSchemaAttribute;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JsonUtilTest {

    @Test
    public void getDataTypeSupportsAllSupportedTypes() {
        for (JsonDataType dataType : JsonDataType.values()) {
            assertEquals(dataType, JsonUtil.getDataType(getJsonNode(dataType)));
        }
    }

    @Test
    public void getDataTypeOnNullInputReturnsNull() {
        assertNull(JsonUtil.getDataType(null));
    }

    @Test
    public void getDataTypeOnInvalidInputReturnsNull() {
        JsonNode jsonNode = getJsonNode(JsonDataType.OBJECT);
        when(jsonNode.get(JsonSchemaAttribute.TYPE.getValue())).thenReturn(null);
        assertNull(JsonUtil.getDataType(jsonNode));
    }

    @Test
    public void getDataTypeWorksWithEnum() {
        JsonNode jsonNode = mock(JsonNode.class);
        when(jsonNode.has(JsonSchemaAttribute.TYPE.getValue())).thenReturn(false);
        when(jsonNode.has(JsonDataType.ENUM.getValue())).thenReturn(true);
        assertEquals(JsonDataType.ENUM, JsonUtil.getDataType(jsonNode));
    }

    private JsonNode getJsonNode(JsonDataType dataType) {
        JsonNode jsonTypeNode = mock(JsonNode.class);
        when(jsonTypeNode.textValue()).thenReturn(dataType.getValue());

        JsonNode jsonNode = mock(JsonNode.class);
        String typeFieldName = JsonSchemaAttribute.TYPE.getValue();
        when(jsonNode.has(typeFieldName)).thenReturn(true);
        when(jsonNode.get(typeFieldName)).thenReturn(jsonTypeNode);

        return jsonNode;
    }

}
