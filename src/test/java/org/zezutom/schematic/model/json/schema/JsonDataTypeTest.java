package org.zezutom.schematic.model.json.schema;

import org.zezutom.schematic.model.EnumWithCustomValueTestCase;

import java.util.function.Function;

public class JsonDataTypeTest extends EnumWithCustomValueTestCase<JsonDataType> {

    @Override
    protected JsonDataType[] values() {
        return JsonDataType.values();
    }

    @Override
    protected Function<String, JsonDataType> get() {
        return JsonDataType::get;
    }

    @Override
    protected Function<String, Boolean> contains() {
        return JsonDataType::contains;
    }

    @Override
    protected Function<String, JsonDataType> valueOf() {
        return JsonDataType::valueOf;
    }
}
