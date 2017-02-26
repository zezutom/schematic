package org.zezutom.schematic.util;

import fabricator.Fabricator;
import org.zezutom.schematic.model.json.schema.JsonStringFormat;

import javax.validation.constraints.NotNull;
import java.util.function.Supplier;

/**
 * Commonly used app functions.
 */
public class AppUtil {

    static final int ZERO = 0;

    private AppUtil() {}

    public static boolean isValidMin(Integer min) {
        return min != null && min >= ZERO;
    }

    public static boolean isValidMax(Integer max) {
        return max != null && max > ZERO;
    }

    public static boolean isValidRange(Integer min, Integer max) {
        return isValidMin(min) && (max != null && max > min);
    }

    public static @NotNull Supplier<String> getValueSupplier(JsonStringFormat format) {

        if (format == null) return RandomUtil::nextStringFromUUID;

        Supplier<String> supplier;

        switch (format) {
            case DATE_TIME:
                supplier = () -> Fabricator.calendar().randomDate().asString();
                break;
            case EMAIL:
                supplier = () -> Fabricator.contact().eMail();
                break;
            case HOSTNAME:
            case URI:
                supplier = () -> Fabricator.internet().urlBuilder().toString();
                break;
            case IPV4:
                supplier = () -> Fabricator.internet().ip();
                break;
            case IPV6:
                supplier = () -> Fabricator.internet().ipv6();
                break;
            default:
                supplier = RandomUtil::nextStringFromUUID;
        }

        return supplier;
    }
}
