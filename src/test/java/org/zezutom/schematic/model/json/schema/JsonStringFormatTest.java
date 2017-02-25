package org.zezutom.schematic.model.json.schema;

import org.zezutom.schematic.model.EnumWithCustomValueTestCase;

import java.util.function.Function;

public class JsonStringFormatTest extends EnumWithCustomValueTestCase<JsonStringFormat> {

    @Override
    protected JsonStringFormat[] values() {
        return JsonStringFormat.values();
    }

    @Override
    protected Function<String, JsonStringFormat> get() {
        return JsonStringFormat::get;
    }

    @Override
    protected Function<String, Boolean> contains() {
        return JsonStringFormat::contains;
    }

    @Override
    protected Function<String, JsonStringFormat> valueOf() {
        return JsonStringFormat::valueOf;
    }
}
