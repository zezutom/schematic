package org.zezutom.schematic.service.generator.json;

import org.zezutom.schematic.model.json.ObjectNode;
import org.zezutom.schematic.service.PrototypedService;
import org.zezutom.schematic.service.generator.ValueGenerator;
import org.zezutom.schematic.service.parser.json.node.ObjectParser;

import java.util.*;

/**
 * Generates an object according to the provided schema constraints.
 * @see org.zezutom.schematic.model.json.schema.properties.JsonStringProperty
 */
@PrototypedService
public class ObjectGenerator extends BaseSchemaGenerator<Map<String, Object>, ObjectNode, ObjectGenerator, ObjectParser> {

    private final Map<String, ValueGenerator> propertiesMap = new HashMap<>();

    private final Set<ValueGenerator> additionalGenerators = new HashSet<>();

    private Integer min;

    private Integer max;

    private final Set<String> requiredProperties = new HashSet<>();

    // Additional properties are allowed by default
    private boolean additionalPropertiesAllowed = true;

    public void addProperty(String name, ValueGenerator generator) {
        if (isInvalidProperty(name) || generator == null) return;
        propertiesMap.put(name, generator);
    }

    public Map<String, ValueGenerator> getPropertiesMap() {
        return Collections.unmodifiableMap(propertiesMap);
    }

    public ValueGenerator getProperty(String name) {
        return propertiesMap.get(name);
    }

    public void addAdditionalGenerator(ValueGenerator generator) {
        if (generator == null || !additionalPropertiesAllowed) return;
        additionalGenerators.add(generator);
    }

    public Set<ValueGenerator> getAdditionalGenerators() {
        return Collections.unmodifiableSet(additionalGenerators);
    }

    private boolean isInvalidProperty(String name) {
        return name == null || name.isEmpty();
    }

    public void addRequiredProperty(String name) {
        if (isInvalidProperty(name)) return;
        requiredProperties.add(name);
    }

    public Set<String> getRequiredProperties() {
        return Collections.unmodifiableSet(requiredProperties);
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public boolean isAdditionalPropertiesAllowed() {
        return additionalPropertiesAllowed;
    }

    public void setAdditionalPropertiesAllowed(boolean additionalPropertiesAllowed) {
        this.additionalPropertiesAllowed = additionalPropertiesAllowed;
    }

    @Override
    public Map<String, Object> next() {
        Map<String, Object> valueMap = new HashMap<>();
        propertiesMap.forEach((k, v) -> valueMap.put(k, v.next()));
        return valueMap;
    }
}
