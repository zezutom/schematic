package org.zezutom.schematic.model.json.schema.property;

import org.zezutom.schematic.model.EnumWithCustomValueTestCase;
import org.zezutom.schematic.model.json.schema.properties.JsonArrayProperty;

import java.util.function.Function;

public class JsonArrayPropertyTest extends EnumWithCustomValueTestCase<JsonArrayProperty> {

    @Override
    protected JsonArrayProperty[] values() {
        return JsonArrayProperty.values();
    }

    @Override
    protected Function<String, JsonArrayProperty> get() {
        return JsonArrayProperty::get;
    }

    @Override
    protected Function<String, Boolean> contains() {
        return JsonArrayProperty::contains;
    }

    @Override
    protected Function<String, JsonArrayProperty> valueOf() {
        return JsonArrayProperty::valueOf;
    }
}
