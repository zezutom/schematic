package org.zezutom.schematic.model.json.schema.property;

import org.zezutom.schematic.model.EnumWithCustomValueTestCase;
import org.zezutom.schematic.model.json.schema.properties.JsonObjectProperty;

import java.util.function.Function;

public class JsonObjectPropertyTest extends EnumWithCustomValueTestCase<JsonObjectProperty> {

    @Override
    protected JsonObjectProperty[] values() {
        return JsonObjectProperty.values();
    }

    @Override
    protected Function<String, JsonObjectProperty> get() {
        return JsonObjectProperty::get;
    }

    @Override
    protected Function<String, Boolean> contains() {
        return JsonObjectProperty::contains;
    }

    @Override
    protected Function<String, JsonObjectProperty> valueOf() {
        return JsonObjectProperty::valueOf;
    }
}
