#! /bin/bash

WAS_HOME="/usr/local/websphere"
WASADMIN_HOME="/home/system/wasadmin"
application="Contacts"
ear="$WAS_HOME/installableApps/contacts.ear"
websphere="/etc/rc.d/init.d/websphere"

"$websphere" stop
rm -f "$WAS_HOME/logs/"*.* "$WASADMIN_HOME/logs/"*.*
"$WAS_HOME/bin/SEAppInstall.sh" -uninstall "$application" -delete true
"$WAS_HOME/bin/SEAppInstall.sh" -install "$ear" -interactive false -ejbDeploy false -precompileJsp true
"$websphere" start
