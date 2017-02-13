package org.zezutom.schematic.service.generator.json;

import com.fasterxml.jackson.databind.JsonNode;
import org.zezutom.schematic.model.json.schema.JsonSchemaCombinationType;
import org.zezutom.schematic.service.generator.ValueGenerator;

import java.util.List;

/**
 * Generates values against a JSON schema.
 */
public interface JsonSchemaGenerator<T> extends ValueGenerator<T> {

    void combine(JsonSchemaCombinationType rule, List<JsonNode> nodes);
}
