package org.zezutom.schematic.service.generator.value;

/**
 * Generates an integral number.
 */
public class IntegerGenerator implements ValueGenerator<Integer> {

    private Integer multipleOf;

    @Override
    public Integer next() {
        return null;
    }

    public Integer getMultipleOf() {
        return multipleOf;
    }

    public void setMultipleOf(Integer multipleOf) {
        this.multipleOf = multipleOf;
    }
}
