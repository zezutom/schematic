package org.zezutom.schematic.service.generator;

import org.zezutom.schematic.model.json.JsonDataType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Generates a list of items, each item can be of a different data type.
 * @see org.zezutom.schematic.model.json.properties.JsonArrayProperty
 */
public class ArrayGenerator implements ValueGenerator<List<Object>> {

    private final List<JsonDataType> items = new ArrayList<>();

    private Boolean additionalItems;

    private Boolean uniqueItems;

    private Integer minItems;

    private Integer maxItems;

    public void addItem(JsonDataType item) {
        if (item == null || items.contains(item)) return;
        items.add(item);
    }

    public List<JsonDataType> getItems() {
        return Collections.unmodifiableList(items);
    }


    public Boolean getAdditionalItems() {
        return additionalItems;
    }

    public void setAdditionalItems(Boolean additionalItems) {
        this.additionalItems = additionalItems;
    }

    public Boolean getUniqueItems() {
        return uniqueItems;
    }

    public void setUniqueItems(Boolean uniqueItems) {
        this.uniqueItems = uniqueItems;
    }

    public Integer getMinItems() {
        return minItems;
    }

    public void setMinItems(Integer minItems) {
        this.minItems = minItems;
    }

    public Integer getMaxItems() {
        return maxItems;
    }

    public void setMaxItems(Integer maxItems) {
        this.maxItems = maxItems;
    }

    @Override
    public List<Object> next() {
        return null;
    }
}
