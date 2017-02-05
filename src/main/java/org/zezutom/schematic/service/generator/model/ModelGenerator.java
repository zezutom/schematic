package org.zezutom.schematic.service.generator.model;

/**
 * Generates a new model instance.
 */
@FunctionalInterface
public interface ModelGenerator<T> {

    /**
     * @return a new model instance
     */
    T next();
}
