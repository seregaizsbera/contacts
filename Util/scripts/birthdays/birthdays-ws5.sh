#! /bin/bash

WAS_HOME=/usr/local/websphere5
WAS_CLASSPATH="$WAS_CLASSPATH:${WAS_HOME}/properties"
WAS_CLASSPATH="$WAS_CLASSPATH:${WAS_HOME}/lib/j2ee.jar"
WAS_CLASSPATH="$WAS_CLASSPATH:${WAS_HOME}/lib/ecutils.jar"
WAS_CLASSPATH="$WAS_CLASSPATH:${WAS_HOME}/lib/naming.jar"
WAS_CLASSPATH="$WAS_CLASSPATH:${WAS_HOME}/lib/namingclient.jar"
WAS_CLASSPATH="$WAS_CLASSPATH:${WAS_HOME}/lib/namingserver.jar"
BIRTHDAYS_JAR="$WAS_HOME/installableApps/birthdays.jar"

exec "${WAS_HOME}/java/bin/java" \
    -classpath "$WAS_CLASSPATH:$BIRTHDAYS_JAR" \
    su.sergey.contacts.birthdays.LookupBirthdays "$@"
