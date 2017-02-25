package org.zezutom.schematic.model.json;

import org.zezutom.schematic.service.generator.json.ObjectGenerator;

import java.util.Collections;
import java.util.Map;

public class ObjectNodeTest extends NodeTestCase<Map<String, Object>, ObjectGenerator, ObjectNode> {

    @Override
    ObjectNode newInstance(String name, ObjectGenerator generator) {
        return new ObjectNode(name, generator);
    }

    @Override
    Class<ObjectGenerator> getGeneratorClass() {
        return ObjectGenerator.class;
    }

    @Override
    Map<String, Object> getTestValue() {
        return Collections.singletonMap("test", 123);
    }
}
