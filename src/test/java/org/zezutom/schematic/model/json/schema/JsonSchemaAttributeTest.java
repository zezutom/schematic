package org.zezutom.schematic.model.json.schema;

import org.zezutom.schematic.model.EnumWithCustomValueTestCase;

import java.util.function.Function;

public class JsonSchemaAttributeTest extends EnumWithCustomValueTestCase<JsonSchemaAttribute> {

    @Override
    protected JsonSchemaAttribute[] values() {
        return JsonSchemaAttribute.values();
    }

    @Override
    protected Function<String, JsonSchemaAttribute> get() {
        return JsonSchemaAttribute::get;
    }

    @Override
    protected Function<String, Boolean> contains() {
        return JsonSchemaAttribute::contains;
    }

    @Override
    protected Function<String, JsonSchemaAttribute> valueOf() {
        return JsonSchemaAttribute::valueOf;
    }
}
