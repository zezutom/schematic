package org.zezutom.schematic.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AppControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void item() throws Exception {
        httpGet("item");
    }

    @Test
    public void items() throws Exception {
        httpGet("items/10");
    }

    @Test
    public void itemsWithDefaultCount() throws Exception {
        httpGet("items/");
    }

    public void httpGet(String resource) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/" + resource)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }
}
