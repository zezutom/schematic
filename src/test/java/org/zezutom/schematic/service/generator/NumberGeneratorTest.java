package org.zezutom.schematic.service.generator;

import java.util.Arrays;
import java.util.List;

public class NumberGeneratorTest extends EnumValueGeneratorTest<Number, NumberGeneratorToDelete> {

    @Override
    NumberGeneratorToDelete getInstance() {
        return new NumberGeneratorToDelete();
    }

    @Override
    List<Number> getChoices() {
        return Arrays.asList(1, 2, 3);
    }
}
