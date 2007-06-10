#! /bin/bash

WAS_HOME=/usr/local/websphere5
WAS_CLASSPATH="$WAS_HOME/properties"
WAS_CLASSPATH="$WAS_CLASSPATH:$WAS_HOME/lib/namingserver.jar"

BIRTHDAYS_JAR="$WAS_HOME/installableApps/birthdays.jar"

exec "$WAS_HOME/java/bin/java" \
    -classpath "$WAS_CLASSPATH:$BIRTHDAYS_JAR" \
    -Djava.security.manager=default \
    -Djava.security.policy=/home/sergey/.cfg/birthdays-ws5.policy \
    -Dwas.home="$WAS_HOME" \
    su.sergey.contacts.birthdays.LookupBirthdays "$@"
