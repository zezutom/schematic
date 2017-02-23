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

    private final Node node;

    @Autowired
    public AppController(Node node) {
        this.node = node;
    }

    @RequestMapping("/next")
    public Object next() {
        return node.getValue();
    }

    @RequestMapping("/next/{count}")
    public Object next(@PathVariable Integer count) {
        if (count == null) count = 1;
        List<Object> values = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            values.add(node.getValue());
        }
        return values;
    }
}
