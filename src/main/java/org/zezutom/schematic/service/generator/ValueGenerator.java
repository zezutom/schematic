package org.zezutom.schematic.service.generator;

/**
 * Provides a uniquely generated value.
 */
public interface ValueGenerator<T> {

    T next();
}
