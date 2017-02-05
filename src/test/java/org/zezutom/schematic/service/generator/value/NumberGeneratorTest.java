package org.zezutom.schematic.service.generator.value;

import java.util.Arrays;
import java.util.List;

public class NumberGeneratorTest extends EnumValueGeneratorTest<Number, NumberGenerator> {

    @Override
    NumberGenerator getInstance() {
        return new NumberGenerator();
    }

    @Override
    List<Number> getChoices() {
        return Arrays.asList(1, 2, 3);
    }
}
