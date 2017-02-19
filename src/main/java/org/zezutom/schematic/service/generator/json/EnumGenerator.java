package org.zezutom.schematic.service.generator.json;

import org.zezutom.schematic.service.PrototypedService;
import org.zezutom.schematic.service.generator.ValueGenerator;
import org.zezutom.schematic.util.RandomUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Picks a random value out of a defined list of values.
 */
@PrototypedService
public class EnumGenerator implements ValueGenerator<Object> {

    private final List<Object> items = new ArrayList<>();

    @Override
    public Object next() {
        if (items.isEmpty()) return null;
        return items.get(RandomUtil.nextInt(items.size()));
    }

    public void addItem(Object item) {
        // null values are accepted
        items.add(item);
    }

    public List<Object> getItems() {
        return Collections.unmodifiableList(items);
    }
}
