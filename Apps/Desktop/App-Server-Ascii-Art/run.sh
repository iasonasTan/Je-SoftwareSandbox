#!/bin/bash
CONFIG_FILE=$1

./compile.sh

echo "Running java code..."
java \
	server/main/Server \
	$CONFIG_FILE
