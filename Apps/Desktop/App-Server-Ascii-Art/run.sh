#!/bin/bash

./compile.sh

echo "Running java code..."

cd out
java server/main/Server res/version_codes.properties
