package org.zezutom.schematic.service.generator.value;

/**
 * Provides a uniquely generated value.
 */
public interface ValueGenerator<T> {

    T next();
}
