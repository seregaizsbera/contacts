#! /bin/bash

port=900

while true; do
    netstat -ltn | awk '{print $4}' | grep -Eq ":$port\$" && break
    sleep 30
done

exec ~/bin/birthdays ~/.cfg/birthdays.properties
