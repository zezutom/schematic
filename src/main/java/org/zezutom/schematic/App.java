package org.zezutom.schematic;

import java.io.IOException;
import java.util.logging.Logger;

import static spark.Spark.port;

/**
 * Starts an embedded web container and provides access to API.
 */
public class App {

    private static final Logger LOGGER = Logger.getLogger("Schematic Web App");

    private static final String ENDPOINT = "/api/v1/next";

//    private static Node parseSchema(String schemaPath) throws IOException {
//        System.out.println("Schema path: '" + schemaPath + "'");
//        return new JsonSchemaParser().parse(schemaPath);
//    }

    public static void main(String[] args) throws IOException {
//        if (args.length == 0) {
//            LOGGER.warning("usage: -Dport=[PORT_NUMBER] -Dschema=[PATH_TO_SCHEMA_FILE]");
//            return;
//        }
        String schema = System.getProperty("schema");
        String portDef = System.getProperty("port");
        System.out.println(String.format("schema: %s, port: %s", schema, portDef));
        if (schema == null) {
            LOGGER.warning("Schema doesn't exist!");
            return;
        }
//        // Load schema definition
//        Node tree = parseSchema(schema);
//
//        // Get hang of a model generator
//        ModelGenerator modelGenerator = new TreeModelGenerator(tree);
//
        // Set server port
        Integer port = Integer.valueOf(System.getProperty("port", "8080"));
        port(port);
//
//        // Set the routes
//        get(ENDPOINT, (req, res) -> modelGenerator.next());

        // Inform the user
        LOGGER.info(String.format("All done! Go to: http://localhost:%d%s", port, ENDPOINT));
    }
}
