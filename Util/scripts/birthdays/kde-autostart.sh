#! /bin/bash

port=8000

while true; do
    netstat -ltn | awk '{print $4}' | grep -Eq ":$port\$" && break
    sleep 30
done

cd "${TMP:-~/trash}"

exec ~/bin/birthdays ~/.cfg/birthdays.properties
