#! /bin/bash

function fatal() {
    echo "Error: $1" >&2
    echo "Press Enter..." >&2
    read
    exit 1
}

if [ "$TERM" = "dumb" ]; then
    exec rxvt -title "psql" -e "$0" "$@"
    exit 1
fi

cd "$(dirname "$1")" || fatal "îÅ ÍÏÇÕ ĞÅÒÅÊÔÉ × ÒÁÂÏŞÕÀ ÄÉÒÅËÔÏÒÉÀ."

echo "Script $1 is about to be executed." >&2
echo "Press Enter to proceed or Ctrl-C to abort..." >&2
read

psql -tq -d contacts1 -U sergey -f "$(basename "$1")" || fatal "óËÒÉĞÔ ÚÁ×ÅÒÛÉÌÓÑ Ó ÏÛÉÂËÁÍÉ."

echo "Press Enter..." >&2
read
