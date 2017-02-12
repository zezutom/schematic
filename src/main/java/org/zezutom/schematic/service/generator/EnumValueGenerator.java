package org.zezutom.schematic.service.generator;

/**
 * Represents any kind of generator, where it makes sense to
 * keep a list of possible values.
 */
public interface EnumValueGenerator<T> extends ValueGenerator<T> {

    void addValue(T value);
}
