#! /bin/bash

WAS_HOME="/usr/local/websphere"
WASADMIN_HOME="/home/system/wasadmin"
application="Contacts"
ear="$WAS_HOME/installableApps/contacts.ear"
websphere="/etc/rc.d/init.d/websphere"
config="$WAS_HOME/config/server-cfg.xml"
wait="${1:-0}"

"$websphere" stop
rm -f "$WASADMIN_HOME/logs/"*.*
"$WAS_HOME/bin/SEAppInstall.sh" -configFile "$config" -uninstall "$application" -delete true

if [ "$wait" = "1" ]; then
    echo -n "Prepare ear-file and then press Enter..."
    read
fi

"$WAS_HOME/bin/SEAppInstall.sh" -configFile "$config" -install "$ear" -interactive false -ejbDeploy false -precompileJsp true
"$websphere" start
"$WAS_HOME/bin/GenPluginCfg.sh" -configFile "$config"
