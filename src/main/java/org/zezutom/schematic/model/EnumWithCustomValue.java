package org.zezutom.schematic.model;

/**
 * Marker interface for any enum with custom string-to-enum value mapping.
 */
public interface EnumWithCustomValue {

    String getValue();

    static<T extends EnumWithCustomValue> T get(String value, T[] constants) {
        for (T constant : constants) {
            if (constant.getValue().equalsIgnoreCase(value)) {
                return constant;
            }
        }
        return null;
    }

    static<T extends EnumWithCustomValue> boolean contains(String value, T[] constants) {
        return get(value, constants) != null;
    }
}
