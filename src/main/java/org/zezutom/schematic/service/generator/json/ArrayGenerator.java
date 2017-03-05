package org.zezutom.schematic.service.generator.json;

import org.springframework.beans.factory.annotation.Autowired;
import org.zezutom.schematic.model.json.ArrayNode;
import org.zezutom.schematic.model.json.schema.properties.JsonArrayProperty;
import org.zezutom.schematic.service.PrototypedService;
import org.zezutom.schematic.service.generator.ValueGenerator;
import org.zezutom.schematic.service.parser.json.node.ArrayParser;
import org.zezutom.schematic.service.parser.json.node.JsonNodeParserFactory;
import org.zezutom.schematic.util.AppUtil;
import org.zezutom.schematic.util.RandomUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Generates a value according to the selected schema picked at random.
 * @see JsonArrayProperty
 */
@PrototypedService
public class ArrayGenerator extends BaseSchemaGenerator<List<Object> , ArrayNode, ArrayGenerator, ArrayParser> {

    private final List<ValueGenerator> items = new ArrayList<>();

    private Boolean additionalItems;

    private Boolean uniqueItems;

    private Integer minItems;

    private Integer maxItems;

    @Autowired
    public ArrayGenerator(JsonNodeParserFactory parserFactory) {
        super(parserFactory);
    }

    public void addItem(ValueGenerator item) {
        if (item == null) return;
        items.add(item);
    }

    public List<ValueGenerator> getItems() {
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
        List<Object> values = items.stream().map(ValueGenerator::next).collect(Collectors.toList());
        if (Boolean.TRUE.equals(uniqueItems)) {
            // Remove duplicates
            values = values.stream().collect(Collectors.toSet()).stream().collect(Collectors.toList());
        }

        if (AppUtil.isValidRange(minItems, maxItems)) {
            int itemCount = RandomUtil.nextInt(minItems, maxItems);
            if (values.size() < itemCount) {
                addRandomValues(values, itemCount - values.size());
            } else {
                values = getRandomValues(values, itemCount);
            }
        } else if (AppUtil.isValidMin(minItems) && values.size() < minItems) {
            addRandomValues(values);
        } else if (AppUtil.isValidMax(maxItems) && values.size() > maxItems) {
            values = trimValues(values);
        } else if (Boolean.TRUE.equals(additionalItems)) {
            // There can be more values beyond what's defined in the schema
            int randomCount = RandomUtil.nextInt(items.size());
            addRandomValues(values, randomCount);
        }
        return values;
    }

    private void addRandomValues(List<Object> values) {
        addRandomValues(values, minItems - values.size());
    }

    private void addRandomValues(List<Object> values, int threshold) {
        for (int i = 0; i < threshold; i++) {
            ValueGenerator generator = items.get(RandomUtil.nextInt(items.size()));
            values.add(generator.next());
        }
    }

    private List<Object> getRandomValues(List<Object> values, int count) {
        List<Object> randomValues = new ArrayList<>();

        while (randomValues.size() <= count) {
            randomValues.add(values.get(RandomUtil.nextInt(values.size())));
        }
        return randomValues;
    }

    private List<Object> trimValues(List<Object> values) {
        return values.subList(0, maxItems);
    }
}
