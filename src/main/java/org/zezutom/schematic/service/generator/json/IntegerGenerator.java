package org.zezutom.schematic.service.generator.json;

import org.zezutom.schematic.model.json.IntegerNode;
import org.zezutom.schematic.service.PrototypedService;
import org.zezutom.schematic.service.parser.json.node.IntegerParser;
import org.zezutom.schematic.util.RandomUtil;

/**
 * Generates an integral number.
 */
@PrototypedService
public class IntegerGenerator extends BaseSchemaGenerator<Integer, IntegerNode, IntegerGenerator, IntegerParser> {

    private Integer multipleOf;

    @Override
    public Integer next() {
        if (multipleOf == null) return RandomUtil.nextInt();
        return RandomUtil.multipleOf(multipleOf);
    }

    public Integer getMultipleOf() {
        return multipleOf;
    }

    public void setMultipleOf(Integer multipleOf) {
        this.multipleOf = multipleOf;
    }
}
