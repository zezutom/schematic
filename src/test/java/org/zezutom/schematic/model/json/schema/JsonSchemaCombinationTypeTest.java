package org.zezutom.schematic.model.json.schema;

import org.zezutom.schematic.model.EnumWithCustomValueTestCase;

import java.util.function.Function;

public class JsonSchemaCombinationTypeTest extends EnumWithCustomValueTestCase<JsonSchemaCombinationType> {

    @Override
    protected JsonSchemaCombinationType[] values() {
        return JsonSchemaCombinationType.values();
    }

    @Override
    protected Function<String, JsonSchemaCombinationType> get() {
        return JsonSchemaCombinationType::get;
    }

    @Override
    protected Function<String, Boolean> contains() {
        return JsonSchemaCombinationType::contains;
    }

    @Override
    protected Function<String, JsonSchemaCombinationType> valueOf() {
        return JsonSchemaCombinationType::valueOf;
    }
}
