[![Build Status](https://travis-ci.org/zezutom/schematic.svg?branch=master)](https://travis-ci.org/zezutom/schematic)
[![Coverage Status](https://coveralls.io/repos/github/zezutom/schematic/badge.svg?branch=master)](https://coveralls.io/github/zezutom/schematic?branch=master)

# Schematic
Takes a schema and generates a data sample. Useful for mock APIs, load testing etc. Currently only supports JSON (http://json-schema.org).

## Installation
```
./gradlew clean build
```
## Usage
```
java -Dschema="/path/to/your_schema.json" -Dport=8088 -jar build/libs/schematic-all-0.1.0.jar 
```
In a browser: http://localhost:8088/api/v1/next

The default port is 8080.

## Examples
### Web Server Logs
The following schema describes a single line of a server log.

**proxy_log_schema.json**
```json
{
  "type": "object",
  "properties": {
    "timestamp": {"type": "number"},
    "ip": {
      "type": "string",
      "oneOf": [
        {"pattern": "([0-9]{1,3}\\.){3}[0-9]{1,3}"},      /* IPv4 */
        {"pattern": "([A-F0-9]{1,4}:){7}[A-F0-9]{1,4}"}   /* IPv6 */
      ]
    },
    "user_agent": {
      "type": "string",
      "oneOf": [
        "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36",
        "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36",
        "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36",
        "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_5) AppleWebKit/601.6.17 (KHTML, like Gecko) Version/9.1.1 Safari/601.6.17",
        "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:47.0) Gecko/20100101 Firefox/47.0"
      ]
    },
    "authenticated": {"type": "boolean"},
    "url": {
      "type": "string",
      "pattern": "https://(acme|acme-store)\\.(com|org)"
    },
    "res_status": {
      "type": "number",
      "enum": [200, 302, 404, 500, 502, 503]
    },
    "res_size": {"type": "number", "minimum": 10}
  }
}
```
To generate a record simulating captured request on a web server.

**Start the app passing the schema file**
```
java -Dschema=proxy_log_schema.json -Dport=8088 -jar build/libs/schematic-all-0.1.0.jar
```

**Open a browser**
http://localhost:8088/api/v1/next

```
{authenticated=true, ip=6:B5BF:44:A62C:0DFE:A:68:ED, res_status=200, res_size=692460237, url=https://acme.com, user_agent=Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_5) AppleWebKit/601.6.17 (KHTML, like Gecko) Version/9.1.1 Safari/601.6.17, timestamp=1288249609}
```
Refresh the page multiple times, you should get a different set of values each time.


More examples to follow..

## JSON Schema Support
The support is very basic at the moment, I am working on adding more [validation keywords](http://json-schema.org/latest/json-schema-validation.html#rfc.section.3.1) for a richer functionality.