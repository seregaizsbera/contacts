#! /bin/bash

database=$1

cd "$(dirname "$0")"

function usage() {
    prog="$(basename "$0")"
    echo "Usage: $prog <database-name>"
    exit 1
}

[ -n "$database" ] || usage

psql -lt | awk  '{print $1}' | grep -Ewq "$database" && echo "Database $database already exists."

cat<<EOF | psql -a 1>cratedb.output 2>&1
\c sergey sergey

CREATE DATABASE $database;
COMMENT ON DATABASE $database IS 'База данных людей, предприятий, организаций, их адресов, телефонов и звонков';

\c $database sergey

\i main.sql
\q
EOF
