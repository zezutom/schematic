package org.zezutom.schematic.service.generator.json;

public class BooleanGeneratorTest extends ValueGeneratorTestCase<Boolean, BooleanGenerator> {

    @Override
    BooleanGenerator newInstance() {
        return new BooleanGenerator();
    }
}
