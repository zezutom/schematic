#!/bin/bash

#
# Starts the server user either a default config or the provided one.
#
# Usage: sh runner.sh -j path/to/the/jar [-c /path/to/your/config/file]
#
# Arguments:
#
# -j path to the jar file       ex.: ~/schematic/schematic-0.1.0.jar
# -c path to the config file    ex.: ~/schematic/custom.properties
#

function usage {
    echo "Usage: -j path/to/the/jar [-c /path/to/your/config/file]"
}

while getopts ":j:c:" opt; do
    case ${opt} in
        j) JAR="$OPTARG"
        ;;
        c) CONFIG_FILE="$OPTARG"
        ;;
        \?) usage
        ;;
    esac
done

if [ -z ${JAR+x} ]; then
    usage
    exit 1
fi

if [ -z ${CONFIG_FILE+x} ]; then
    java -jar ${JAR} org.zezutom.schematic.App
else
    java -Dspring.config.location=${CONFIG_FILE} -jar ${JAR} org.zezutom.schematic.App
fi

