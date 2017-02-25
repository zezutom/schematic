package org.zezutom.schematic.model.json.schema.property;

import org.zezutom.schematic.model.EnumWithCustomValueTestCase;
import org.zezutom.schematic.model.json.schema.properties.JsonStringProperty;

import java.util.function.Function;

public class JsonStringPropertyTest extends EnumWithCustomValueTestCase<JsonStringProperty> {

    @Override
    protected JsonStringProperty[] values() {
        return JsonStringProperty.values();
    }

    @Override
    protected Function<String, JsonStringProperty> get() {
        return JsonStringProperty::get;
    }

    @Override
    protected Function<String, Boolean> contains() {
        return JsonStringProperty::contains;
    }

    @Override
    protected Function<String, JsonStringProperty> valueOf() {
        return JsonStringProperty::valueOf;
    }
}
