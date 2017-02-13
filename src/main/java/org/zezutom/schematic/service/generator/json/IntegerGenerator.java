package org.zezutom.schematic.service.generator.json;

import org.zezutom.schematic.model.json.IntegerNode;
import org.zezutom.schematic.service.parser.json.IntegerParser;

/**
 * Generates an integral number.
 */
public class IntegerGenerator extends BaseSchemaGenerator<Integer, IntegerNode, IntegerGenerator, IntegerParser> {

    private Integer multipleOf;

    public IntegerGenerator(IntegerParser parser) {
        super(parser);
    }

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
