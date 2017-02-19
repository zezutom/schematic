package org.zezutom.schematic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zezutom.schematic.model.json.Node;

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
}
