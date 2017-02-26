package org.zezutom.schematic.service.generator.json;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Test;
import org.zezutom.schematic.TestUtil;
import org.zezutom.schematic.model.json.schema.JsonDataType;
import org.zezutom.schematic.model.json.schema.JsonSchemaCombinationType;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;

public abstract class SchemaGeneratorTestCase<T, G extends JsonSchemaGenerator<T, G>> extends ValueGeneratorTestCase<T, G> {

    @Test
    public void combineOnNullCombinationTypeHasNoEffect() {
        List<JsonNode> jsonNodes = Collections.singletonList(TestUtil.getJsonNode(JsonDataType.STRING));
        generator.combine(null, jsonNodes);
        assertNull(generator.getCombinationRule());
    }

    @Test
    public void combineOnNullJsonNodesHasNoEffect() {
        generator.combine(JsonSchemaCombinationType.ALL_OF, null);
        assertNull(generator.getCombinationRule());
    }

    @Test
    public void combineOnEmptyJsonNodesHasNoEffect() {
        generator.combine(JsonSchemaCombinationType.ONE_OF, Collections.emptyList());
        assertNull(generator.getCombinationRule());
    }

    @Test
    public void combineOnInvalidInputHasNoEffect() {
        generator.combine(null, Collections.emptyList());
        assertNull(generator.getCombinationRule());
    }

    @Test
    public void combineOnNonCompliantJsonNodesHasNoEffect() {
        List<JsonNode> nonCompliantJsonNodes = Collections.singletonList(mock(JsonNode.class));
        generator.combine(JsonSchemaCombinationType.ONE_OF, nonCompliantJsonNodes);
        assertNull(generator.getCombinationRule());
    }
}
