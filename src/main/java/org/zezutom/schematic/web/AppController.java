package org.zezutom.schematic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zezutom.schematic.model.json.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * Web API for schema-based document generation.
 */
@RestController
@RequestMapping("/api/v1")
public class AppController {

    static final int DEFAULT_COUNT = 1;

    private final Node node;

    @Autowired
    public AppController(Node node) {
        this.node = node;
    }

    @RequestMapping("/item")
    public Object item() {
        return node.getValue();
    }

    @RequestMapping(value = {"/items", "/items/{count}"})
    public List<Object> items(@PathVariable(required = false) Integer count) {
        if (count == null) count = DEFAULT_COUNT;
        List<Object> values = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            values.add(node.getValue());
        }
        return values;
    }
}
