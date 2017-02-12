package org.zezutom.schematic.service.generator.json;

import org.zezutom.schematic.service.generator.ValueGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Generates a fixed set of values according to the provided schema constraints.
 *
 */
public class EnumGenerator implements ValueGenerator<Object> {

    private final List<Object> items = new ArrayList<>();

    @Override
    public Object next() {
        return null;
    }

    public void addItem(Object item) {
        // null values are accepted
        items.add(item);
    }

    public List<Object> getItems() {
        return Collections.unmodifiableList(items);
    }
}
