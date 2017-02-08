package org.zezutom.schematic.service.generator.value;

import org.zezutom.schematic.model.json.JsonDataType;

import java.util.ArrayList;
import java.util.List;

/**
 * Generates a fixed set of values according to the provided schema constraints.
 *
 */
public class EnumGenerator implements ValueGenerator<Object> {

    private final List<Object> items = new ArrayList<>();

    private final JsonDataType dataType;

    public EnumGenerator() {
        this(null);
    }

    public EnumGenerator(JsonDataType dataType) {
        this.dataType = dataType;
    }

    @Override
    public Object next() {
        return null;
    }

    public void addItem(Object item) {
        if (item == null) return;
        items.add(item);
    }

    public JsonDataType getDataType() {
        return dataType;
    }
}
