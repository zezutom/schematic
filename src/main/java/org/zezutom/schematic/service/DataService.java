package org.zezutom.schematic.service;

import org.springframework.stereotype.Service;
import org.zezutom.schematic.model.json.schema.JsonStringFormat;
import org.zezutom.schematic.util.AppUtil;

import java.util.*;
import java.util.function.Supplier;

/**
 * Stores pre-generated data sets.
 */
@Service
public class DataService {

    private final Map<JsonStringFormat, List<String>> valueMap = new EnumMap<>(JsonStringFormat.class);

    public DataService() {
    }

    public DataService(Integer volume, List<JsonStringFormat> formats) {
        preLoad(volume, formats);
    }

    Map<JsonStringFormat, List<String>> getValueMap() {
        return Collections.unmodifiableMap(valueMap);
    }

    public List<String> get(JsonStringFormat format) {
        return valueMap.get(format);
    }

    public void preLoad(Integer volume, List<JsonStringFormat> formats) {

        if (volume <= 0 || formats == null) return;

        formats.stream().filter(Objects::nonNull).forEach(format -> {
            Supplier<String> supplier = AppUtil.getValueSupplier(format);

            List<String> values = new ArrayList<>();
            for (int i = 0; i < volume; i++) {
                values.add(supplier.get());
            }
            valueMap.put(format, values);
        });
    }

}
