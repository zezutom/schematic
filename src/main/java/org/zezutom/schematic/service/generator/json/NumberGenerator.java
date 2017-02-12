package org.zezutom.schematic.service.generator.json;

import org.zezutom.schematic.model.NumberNode;
import org.zezutom.schematic.service.parser.json.NumberParser;

/**
 * Generates a numeric value according to the provided schema constraints.
 * @see org.zezutom.schematic.model.json.properties.JsonNumericProperty
 */
public class NumberGenerator extends BaseSchemaGenerator<Number, NumberNode, NumberGenerator, NumberParser> {

    private Boolean exclusiveMaximum = false;
    private Boolean exclusiveMinimum = false;
    private Number minimum;
    private Number maximum;
    private Integer multipleOf;

    public NumberGenerator(NumberParser parser) {
        super(parser);
    }

    @Override
    public Number next() {
        return null;
    }

    public Boolean getExclusiveMaximum() {
        return exclusiveMaximum;
    }

    public Boolean getExclusiveMinimum() {
        return exclusiveMinimum;
    }

    public Number getMinimum() {
        return minimum;
    }

    public Number getMaximum() {
        return maximum;
    }

    public Integer getMultipleOf() {
        return multipleOf;
    }

    public void setExclusiveMaximum(Boolean exclusiveMaximum) {
        this.exclusiveMaximum = exclusiveMaximum;
    }

    public void setExclusiveMinimum(Boolean exclusiveMinimum) {
        this.exclusiveMinimum = exclusiveMinimum;
    }

    public void setMinimum(Number minimum) {
        this.minimum = minimum;
    }

    public void setMaximum(Number maximum) {
        this.maximum = maximum;
    }

    public void setMultipleOf(Integer multipleOf) {
        this.multipleOf = multipleOf;
    }
}
