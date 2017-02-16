package org.zezutom.schematic.service.generator.json;

import org.zezutom.schematic.model.json.NumberNode;
import org.zezutom.schematic.service.parser.json.NumberParser;
import org.zezutom.schematic.util.RandomUtil;

/**
 * Generates a numeric value according to the provided schema constraints.
 * @see org.zezutom.schematic.model.json.schema.properties.JsonNumericProperty
 */
public class NumberGenerator extends BaseSchemaGenerator<Number, NumberNode, NumberGenerator, NumberParser> {

    private static final int MULTIPLIER_MIN = 1;

    private static final int MULTIPLIER_MAX = 10;

    private Boolean exclusiveMaximum = false;

    private Boolean exclusiveMinimum = false;

    private Number minimum;

    private Number maximum;

    private Integer multipleOf;

    NumberGenerator() {
        super(null);
    }
    public NumberGenerator(NumberParser parser) {
        super(parser);
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

    @Override
    public Number next() {

        Number result = null;

        if (minimum != null && maximum != null) {
            int minValue = minimum.intValue();
            int maxValue = maximum.intValue();

            if (maxValue > minValue) {
                result = RandomUtil.nextInt(minValue, maxValue, exclusiveMinimum, exclusiveMaximum);
            }
        } else if (minimum != null) {
            result = RandomUtil.nextMin(minimum.intValue(), exclusiveMinimum);
        } else if (maximum != null) {
            result = RandomUtil.nextMax(maximum.intValue(), exclusiveMaximum);
        } else if (multipleOf != null) {
            result = multipleOf * RandomUtil.nextInt(MULTIPLIER_MIN, MULTIPLIER_MAX);
        } else {
            result = RandomUtil.nextInt();
        }

        return result;
    }

}
