#! /bin/bash

WAS_HOME=/usr/local/websphere
WAS_CLASSPATH="$WAS_HOME/lib/ejbcontainer.jar"
WAS_CLASSPATH="$WAS_CLASSPATH:$WAS_HOME/lib/j2ee.jar"
WAS_CLASSPATH="$WAS_CLASSPATH:$WAS_HOME/lib/iwsorb.jar"
WAS_CLASSPATH="$WAS_CLASSPATH:$WAS_HOME/lib/websphere.jar"
BIRTHDAYS_JAR="$WAS_HOME/installedApps/birthdays.jar"

exec "${WAS_HOME}/java/bin/java" \
    -classpath "$WAS_CLASSPATH:$BIRTHDAYS_JAR" \
    su.sergey.contacts.birthdays.LookupBirthdays "$@"
