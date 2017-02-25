package org.zezutom.schematic.model.json;

import org.junit.Test;
import org.zezutom.schematic.service.generator.json.NullGenerator;

import javax.lang.model.type.NullType;

import static org.junit.Assert.assertNull;

public class NullNodeTest extends NodeTestCase<NullType, NullGenerator, NullNode> {

    @Override
    NullNode newInstance(String name, NullGenerator generator) {
        return new NullNode(name, generator);
    }

    @Override
    Class<NullGenerator> getGeneratorClass() {
        return NullGenerator.class;
    }

    @Override
    NullType getTestValue() {
        return null;
    }

    @Test
    @Override
    public void getValue() {
        assertNull(node.getValue());
    }
}
