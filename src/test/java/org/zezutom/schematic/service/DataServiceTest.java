package org.zezutom.schematic.service;

import org.junit.Test;
import org.zezutom.schematic.model.json.schema.JsonStringFormat;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class DataServiceTest {

    private DataService dataService;

    private void newInstance(Integer volume, List<JsonStringFormat> formats) {
        dataService = new DataService(volume, formats);
    }

    @Test
    public void preLoad() {
        newInstance(10, Arrays.asList(JsonStringFormat.values()));
        for (JsonStringFormat format : JsonStringFormat.values()) {
            assertNotNull(dataService.getValueMap().get(format));
        }
    }

    @Test
    public void preLoadOnZeroVolumeHasNoEffect() {
        newInstance(0, Collections.singletonList(JsonStringFormat.EMAIL));
        assertValueMapIsEmpty();
    }

    @Test
    public void preLoadOnNegativeVolumeHasNoEffect() {
        newInstance(-1, Collections.singletonList(JsonStringFormat.EMAIL));
        assertValueMapIsEmpty();
    }

    @Test
    public void preLoadOnNullFormatListHasNoEffect() {
        newInstance(10, null);
        assertValueMapIsEmpty();
    }

    @Test
    public void preLoadOnZeroVolumeAndNullFormatListHasNoEffect() {
        newInstance(0, null);
        assertValueMapIsEmpty();
    }

    @Test
    public void preLoadOnNegativeVolumeAndNullFormatListHasNoEffect() {
        newInstance(-1, null);
        assertValueMapIsEmpty();
    }

    @Test
    public void preLoadOnNullFormatHasNoEffect() {
        newInstance(10, Collections.singletonList(null));
        assertValueMapIsEmpty();
    }

    private void assertValueMapIsEmpty() {
        assertTrue(dataService.getValueMap().isEmpty());
    }
}
