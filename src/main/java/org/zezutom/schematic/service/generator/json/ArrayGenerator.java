package org.zezutom.schematic.service.generator.json;

import org.zezutom.schematic.model.json.ArrayNode;
import org.zezutom.schematic.model.json.schema.JsonDataType;
import org.zezutom.schematic.service.parser.json.ArrayParser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Generates a list of items, each item can be of a different data type.
 * @see org.zezutom.schematic.model.json.schema.properties.JsonArrayProperty
 */
public class ArrayGenerator extends BaseSchemaGenerator<List<Object>, ArrayNode, ArrayGenerator, ArrayParser> {

    private final List<JsonDataType> items = new ArrayList<>();

    private Boolean additionalItems;

    private Boolean uniqueItems;

    private Integer minItems;

    private Integer maxItems;

    public ArrayGenerator(ArrayParser parser) {
        super(parser);
    }

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
