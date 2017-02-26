package org.zezutom.schematic.service;

import org.junit.Before;
import org.junit.Test;
import org.zezutom.schematic.model.json.schema.JsonStringFormat;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class DataServiceTest {

    private DataService dataService;

    @Before
    public void before() {
        dataService = new DataService();
    }

    @Test
    public void preLoad() {
        dataService.preLoad(10, Arrays.asList(JsonStringFormat.values()));
        for (JsonStringFormat format : JsonStringFormat.values()) {
            assertNotNull(dataService.getValueMap().get(format));
        }
    }

    @Test
    public void preLoadOnZeroVolumeHasNoEffect() {
        dataService.preLoad(0, Collections.singletonList(JsonStringFormat.EMAIL));
        assertValueMapIsEmpty();
    }

    @Test
    public void preLoadOnNegativeVolumeHasNoEffect() {
        dataService.preLoad(-1, Collections.singletonList(JsonStringFormat.EMAIL));
        assertValueMapIsEmpty();
    }

    @Test
    public void preLoadOnNullFormatListHasNoEffect() {
        dataService.preLoad(10, null);
        assertValueMapIsEmpty();
    }

    @Test
    public void preLoadOnZeroVolumeAndNullFormatListHasNoEffect() {
        dataService.preLoad(0, null);
        assertValueMapIsEmpty();
    }

    @Test
    public void preLoadOnNegativeVolumeAndNullFormatListHasNoEffect() {
        dataService.preLoad(-1, null);
        assertValueMapIsEmpty();
    }

    @Test
    public void preLoadOnNullFormatHasNoEffect() {
        dataService.preLoad(10, Collections.singletonList(null));
        assertValueMapIsEmpty();
    }

    private void assertValueMapIsEmpty() {
        assertTrue(dataService.getValueMap().isEmpty());
    }
}
