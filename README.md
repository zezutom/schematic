[![Build Status](https://travis-ci.org/zezutom/schematic.svg?branch=master)](https://travis-ci.org/zezutom/schematic)
[![Coverage Status](https://coveralls.io/repos/github/zezutom/schematic/badge.svg?branch=master)](https://coveralls.io/github/zezutom/schematic?branch=master)

# Schematic
Takes a schema and generates a data sample. Useful for mock APIs, load testing etc. Currently only supports JSON (http://json-schema.org).

## Installation
```
./gradlew clean build
```
## Usage
### Run with Default Settings
There is a sample schema built into the jar, which is handy if you want to explore the app at first.

Using plain java:
```
java -jar build/libs/schematic-all-0.1.0.jar org.zezutom.schematic.App
```
Alternatively, you can use the provided 'runner' script:
```
sh ./runner.sh -j build/libs/schematic-all-0.1.0.jar
```
In a browser: http://localhost:8080/api/v1/next

The default port is 8080.

### Run with Custom Settings
```
java -Dspring.config.location=/path/to/my.properties -jar build/libs/schematic-0.1.0.jar org.zezutom.schematic.App
```
Or, more conveniently, use the runner:
```
sh ./runner.sh -j build/libs/schematic-all-0.1.0.jar -c /path/to/my.properties
```
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
        {"format": "ipv4"},
        {"format": "ipv6"}
      ]
    },
    "user_agent": {
      "enum": [
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
      "format": "hostname"
    },
    "res_status": {
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
http://localhost:8080/api/v1/item

```json
{"authenticated":true,"ip":"23.160.116.223","res_status":503,"res_size":2962,"user_agent":"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:47.0) Gecko/20100101 Firefox/47.0","url":"http://specifiesstroke.dm/getEntity?q=test","timestamp":1689761594}
```
Refresh the page multiple times, you should get a different set of values each time.

## Load Testing
The tool is built with load testing in mind. Admittedly, some values, such as an IP address or a hostname, are expensive to generate. Hence, config allows to preload certain types of values in advance. With a pool of cached values, loading hundrets or even thousands of items is blazing fast. 

With a pool of 100 pre-generated IPs, hostnames etc., getting a thousand of lines of a proxy log is instant.

http://localhost:8080/api/v1/items/1000

```json
[{"authenticated":false,"ip":"85.144.10.28","res_status":500,"res_size":4494,"user_agent":"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:47.0) Gecko/20100101 Firefox/47.0","url":"http://toysprofitable.sc/getEntity?q=test","timestamp":1900212822},{"authenticated":false,"ip":"48.105.5.32","res_status":200,"res_size":3827,"user_agent":"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36","url":"http://SXplea.cx/getEntity?q=test","timestamp":-1205228124},{"authenticated":false,"ip":"A9f0:98e4:e48d:b836:df97:3558:e108:cDAC","res_status":503,"res_size":8747,"user_agent":"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36","url":"http://stakessocialist.om/getEntity?q=test","timestamp":2082630060},{"authenticated":true,"ip":"181.150.60.127","res_status":500,"res_size":5955,"user_agent":"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36","url":"http://affairsban.sc/getEntity?q=test","timestamp":-23645349},{"authenticated":false,"ip":"243.217.5.223","res_status":503,"res_size":6065,"user_agent":"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36","url":"http://Mandelalend.gh/getEntity?q=test","timestamp":660852509},{"authenticated":true,"ip":"14.33.99.238","res_status":502,"res_size":9438,"user_agent":"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:47.0) Gecko/20100101 Firefox/47.0","url":"http://SXplea.cx/getEntity?q=test","timestamp":2058162337},
...
```

## JSON Schema Support
The support is very basic at the moment, I am working on adding more [validation keywords](http://json-schema.org/latest/json-schema-validation.html#rfc.section.3.1) for a richer functionality.
