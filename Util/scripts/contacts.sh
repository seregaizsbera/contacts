#! /bin/bash

cd
if [ "$UID" == 0 ]; then
    umask -S ug=rwx,o=
fi
exec developer -data /home/sergey/prog/workspaces/contacts "$@"
