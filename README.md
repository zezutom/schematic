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
The tool was developed with load testing in mind. Since the generated content should resemble real-life data, some values are expensive to generate, for instance an IP address or a hostname. To cater for a consistently good performance, the config allows to preload certain types of values in advance. With a pool of cached values, loading hundrets or even thousands of items remains sufficiently fast. 

For example, with a pool of one hundred of pre-generated IPs, hostnames, email addresses etc., generating a lengthy proxy log is instant:

http://localhost:8080/api/v1/items/1000

```json
[
   {
      "authenticated":true,
      "ip":"AA7F:C5e3:63F3:BD1f:C7CC:DbEC:C730:C1Cd",
      "res_status":404,
      "res_size":2071,
      "user_agent":"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36",
      "url":"http://fightersSometimes.tz/getEntity?q=test",
      "timestamp":738716088
   },
   {
      "authenticated":true,
      "ip":"107.110.157.53",
      "res_status":302,
      "res_size":197,
      "user_agent":"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36",
      "url":"http://acquirednodes.info/getEntity?q=test",
      "timestamp":646430459
   },
   {
      "authenticated":false,
      "ip":"72.38.174.17",
      "res_status":404,
      "res_size":6602,
      "user_agent":"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:47.0) Gecko/20100101 Firefox/47.0",
      "url":"http://Mrsarrange.gm/getEntity?q=test",
      "timestamp":1179608603
   },
   {
      "authenticated":true,
      "ip":"3CFF:30b4:542a:Fc56:38dB:5E6B:3126:C0cf",
      "res_status":503,
      "res_size":1106,
      "user_agent":"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_5) AppleWebKit/601.6.17 (KHTML, like Gecko) Version/9.1.1 Safari/601.6.17",
      "url":"http://FoundationHelp.ci/getEntity?q=test",
      "timestamp":894236378
   },
   {
      "authenticated":false,
      "ip":"133.120.247.229",
      "res_status":404,
      "res_size":3689,
      "user_agent":"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_5) AppleWebKit/601.6.17 (KHTML, like Gecko) Version/9.1.1 Safari/601.6.17",
      "url":"http://leaksrevenue.bd/getEntity?q=test",
      "timestamp":-1030984564
   },
   {
      "authenticated":true,
      "ip":"253.53.31.191",
      "res_status":302,
      "res_size":6461,
      "user_agent":"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36",
      "url":"http://Unisysproposed.biz/getEntity?q=test",
      "timestamp":1364563783
   },
...
```

## JSON Schema Support

### Data Types
#### String
##### Basic Random String
Generates a pseudorandom string from an UUID.
```json
{ "type": "string" }
```
Examples of generated values: 
```
0dbbb161-ca04-448c-9352-c703a3e5f45f
39478181-bd26-4220-875b-397b56a4cc0f
6d26c9e2-5b52-4c89-aa8d-a79b879facf5
...
```
##### Min / Max Length
Generates a pseudorandom string whose length fits within the given boundaries, inclusive.
```json
{
  "type": "string",
  "minLength": 2,
  "maxLength": 3
}
```
Examples of generated values:
```
NeH
wR
ZNg
...
```
##### Regex
Generates a random string according to the provided pattern.
```json
{
  "type": "string",
  "pattern": "(\\([0-9]{3}\\))?[0-9]{3}-[0-9]{4}"
}
```
Examples of generated values:
```
(427)258-9342
(455)365-4365
(490)812-5120
...
```
#### String Format
Supported formats:
* date / time
* hostname
* ipv4 and ipv6
* email

Generated values of the respective format are provided by Fabricator.

##### Date / Time
Generates a formatted date as 'MM-dd-yyyy'.
```json
{
  "type": "string",
  "format": "date-time"
}
```
Examples of generated values:
```
18-07-2014
10-10-2014
04-10-1976
...
```

##### Hostname
```json
{
  "type": "string",
  "format": "hostname"
}
```
Examples of generated values:
```
http://surfacedVendors.hr/getEntity?q=test
http://coachworld's.ee/getEntity?q=test
http://patentsprisoners.tl/getEntity?q=test
...
```

##### IPv4
```json
{
  "type": "string",
  "format": "ipv4"
}
```
Examples of generated values:
```
80.104.210.179
227.254.136.166
183.16.0.17
...
```

##### IPv6
```json
{
  "type": "string",
  "format": "ipv6"
}
```
Examples of generated values:
```
bb6C:9BDb:A644:479e:606B:9f12:aCEE:aefa
7EF6:cCbf:eba2:CdBb:a03f:a7f0:FD9c:AE3E
B3c3:E5F9:59B9:e6e1:4FE5:affC:4a77:5C5E
...
```

##### Email
```json
{
  "type": "string",
  "format": "email"
}
```
Examples of generated values:
```
nasir_raynor418@gmail.com
jarret_quigley411@hotmail.com
kendrick_kerluke690@hotmail.com
...
```

#### Numeric Types
##### Random Number
Generates a pseudorandom integer, _java.util.Random.nextInt()_ is used under the hood.
```json
{
  "type": "number"
}
```
Examples of generated values:
```
-2132414927
-582023836
1366993374
```
##### Numeric Inclusive Range
Generates a pseudorandom integer within the given range, inclusive.
```json
{
  "type": "number",
  "minimum": 0,
  "maximum": 100
}
```
Examples of generated values:
```
15
93
32
...
```
##### Numeric Exclusive Range
Generates a pseudorandom integer with the given range. Either end can be specified as exclusive.

Exclusive minimum:
```json
{
  "type": "number",
  "minimum": 0,
  "maximum": 100,
  "exclusiveMinimum": true
}
```

Exclusive maximum:
```json
{
  "type": "number",
  "minimum": 0,
  "maximum": 100,
  "exclusiveMaximum": true
}
```

Both, min and max are exlusive:
```json
{
  "type": "number",
  "minimum": 0,
  "maximum": 100,
  "exclusiveMinimum": true,
  "exclusiveMaximum": true
}
```
Examples of generated values:
```
82
78
64
...
```

#### Boolean
```json
{ "type": "boolean" }
```
Returns either _true_ or _false_ at (pseudo) random.

#### Object
Objects are essentially key-value pairs of properties, where values are either primitive data types or nested objects.

In its simplest form, an object is defined as:
```json
{ "type": "object" }
```
However, declaring a mere object type isn't too useful, at it always results into an empty object _{}_. Let's take a look at other, more practical, use cases.

##### Random Address
Let's define an address in a fictional city as a combination of a templated street name (regex), a street number ranging between 1 and 100 and a type.
```json
{
  "type": "object",
  "properties": {
    "number":      { "type": "number", "minimum": 1, "maximum": 100 },
    "street_name": { "type": "string", "pattern":"[A-Z][a-z]+ (Rd\\.|St\\.)" },
    "street_type": {
      "enum": ["Street", "Avenue", "Boulevard"]
    }
  }
}
```
Examples of generated values:
```
{number=64, street_type=Boulevard, street_name=Ost Rd.}
{number=97, street_type=Street, street_name=Zum St.}
{number=13, street_type=Street, street_name=Hnq Rd.}
...
```
##### Web Proxy Log
A proxy log schema is a good example of a more complex object. A single line of the log comprises a timestamp, IP address (v4 or v6), user agent, HTTP response status and size of the returned response.
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
Example of a generated value:
```json
{
   "authenticated":false,
   "ip":"b5d9:ea6D:F315:bEFC:B2CE:7af2:cff9:2ee2",
   "res_status":500,
   "res_size":269,
   "user_agent":"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_5) AppleWebKit/601.6.17 (KHTML, like Gecko) Version/9.1.1 Safari/601.6.17",
   "url":"http://descriptionsshouldn't.cr/getEntity?q=test",
   "timestamp":1104818113
}
```

#### Array
An array can be seen as a list of items. Both primitive and complex data types are supported.

In its simplest form, an array is defined as:
```json
{ "type": "array" }
```
That's not too useful however, since it always results into an empty array _[]_. Let's explore some of more practical examples.

##### Bunch of Random Numbers
Suppose we need 2 to 10 of pseudorandom numbers, we define the schema as follows.
```json
{
  "type": "array",
  "minItems": 2,
  "maxItems": 10,
  "items": {
    "type": "number"
  }
}
```
Example of a generated values:
```json
[20630503, 400700874]
[20630503, 400700874, 1173509128, 1628650785]
[-620837239,1529303543,-1122104159]
...
```


#### Null
### Enumerated Values
### Schema Combinations
