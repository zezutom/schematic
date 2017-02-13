package org.zezutom.schematic.model.json.schema;

import org.zezutom.schematic.service.generator.ValueGenerator;

import java.util.Collections;
import java.util.List;

/**
 * Defines how to combine value generators in such a way, that it complies to the given combination type.
 * @see JsonSchemaCombinationType
 */
public class JsonSchemaCombinationRule<T extends ValueGenerator> {

    private final List<T> generators;

    private final JsonSchemaCombinationType type;

    public JsonSchemaCombinationRule(JsonSchemaCombinationType type, List<T> generators) {
        this.type = type;
        this.generators = generators;
    }

    public List<T> getGenerators() {
        return Collections.unmodifiableList(generators);
    }

    public JsonSchemaCombinationType getType() {
        return type;
    }
}
