#! /bin/bash

database="$1"
user="$USER"

cd "$(dirname "$0")"

function usage() {
    prog="$(basename "$0")"
    echo "Usage: $prog <database-name>"
    exit 1
}

[ -n "$database" ] || usage

createdb "$database" "База данных людей, предприятий, организаций, их адресов, телефонов и звонков" || exit 1

cat<<EOF | psql -a -d "$database" 1>cratedb.output 2>&1
\c - $user
\i main.sql
\q
EOF
