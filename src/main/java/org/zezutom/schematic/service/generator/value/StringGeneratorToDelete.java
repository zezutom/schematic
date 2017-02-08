package org.zezutom.schematic.service.generator.value;

import com.mifmif.common.regex.Generex;
import org.zezutom.schematic.util.RandomUtil;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.UUID;

/**
 * Produces a new textual value according to the parsing rules.
 */
public class StringGeneratorToDelete implements EnumValueGenerator<String> {

    private final Map<String, Boolean> valueMap = new TreeMap<>();

    public void addValue(String value) {
        addValue(value, false);
    }

    public void addValue(String value, boolean isPattern) {
        if (value == null || value.isEmpty()) {
            return;
        }
        valueMap.put(value, isPattern);
    }

    @Override
    public String next() {

        Optional<String> valueOptional;

        if (valueMap.isEmpty()) {
            // No value has been assigned at all
            valueOptional = Optional.empty();
        } else if (valueMap.size() == 1) {
            // There is just a single value, fetch it!
            valueOptional = valueMap.keySet().stream().findFirst();
        } else {
            // Pick one of the values at random
            valueOptional = RandomUtil.getRandomValue(valueMap.keySet());
        }

        String value;

        if (valueOptional.isPresent()) {

            String key = valueOptional.get();

            // Does the value represent a regex?
            if (valueMap.get(key)) {
                value = new Generex(key).random();
            } else {
                value = valueOptional.get();
            }

        } else {
            // No value has been found, generate a default random value
            value = UUID
                    .randomUUID()
                    .toString()
                    .replace("-", "");
        }

        return value;
    }
}
