package org.zezutom.schematic.service.generator.value;

import org.zezutom.schematic.util.RandomUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NumberGeneratorToDelete implements EnumValueGenerator<Number> {

    private final List<Number> values = new ArrayList<>();

    private Integer min;

    @Override
    public Number next() {

        Optional<Number> valueOptional;

        if (values.isEmpty()) {
            valueOptional = (min == null) ? Optional.empty() : Optional.of(RandomUtil.nextInt(min));
        } else if (values.size() == 1) {
            valueOptional = values.stream().findFirst();
        } else {
            valueOptional = RandomUtil.getRandomValue(values);
        }

        return valueOptional.orElse(RandomUtil.nextInt());
    }

    @Override
    public void addValue(Number value) {
        if (value == null) {
            return;
        }
        values.add(value);
    }

    public void setMin(Integer min) {
        this.min = min;
    }
}
