package org.zezutom.schematic.model.json.schema.property;

import org.zezutom.schematic.model.EnumWithCustomValueTestCase;
import org.zezutom.schematic.model.json.schema.properties.JsonNumericProperty;

import java.util.function.Function;

public class JsonNumericPropertyTest extends EnumWithCustomValueTestCase<JsonNumericProperty> {

    @Override
    protected JsonNumericProperty[] values() {
        return JsonNumericProperty.values();
    }

    @Override
    protected Function<String, JsonNumericProperty> get() {
        return JsonNumericProperty::get;
    }

    @Override
    protected Function<String, Boolean> contains() {
        return JsonNumericProperty::contains;
    }

    @Override
    protected Function<String, JsonNumericProperty> valueOf() {
        return JsonNumericProperty::valueOf;
    }
}
