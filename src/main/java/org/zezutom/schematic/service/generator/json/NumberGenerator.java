package org.zezutom.schematic.service.generator.json;

import org.springframework.beans.factory.annotation.Autowired;
import org.zezutom.schematic.model.json.NumberNode;
import org.zezutom.schematic.model.json.schema.properties.JsonNumericProperty;
import org.zezutom.schematic.service.PrototypedService;
import org.zezutom.schematic.service.parser.json.node.JsonNodeParserFactory;
import org.zezutom.schematic.service.parser.json.node.NumberParser;
import org.zezutom.schematic.util.RandomUtil;

/**
 * Generates a numeric value according to the provided schema constraints.
 * @see JsonNumericProperty
 */
@PrototypedService
public class NumberGenerator extends BaseSchemaGenerator<Number, NumberNode, NumberGenerator, NumberParser> {

    private Boolean exclusiveMaximum = false;

    private Boolean exclusiveMinimum = false;

    private Number minimum;

    private Number maximum;

    private Integer multipleOf;

    @Autowired
    public NumberGenerator(JsonNodeParserFactory parserFactory) {
        super(parserFactory);
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
            result = RandomUtil.multipleOf(multipleOf);
        } else {
            result = RandomUtil.nextInt();
        }

        return result;
    }

}
