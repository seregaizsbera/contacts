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

database="$(cat $(dirname "$0")/../database.properties)" || fatal "�� ���� ����� ���� ������"
cd "$(dirname "$1")" || fatal "�� ���� ������� � ������� ����������."

echo "Script $1 is about to be executed on database $database." >&2
echo "Press Enter to proceed or Ctrl-C to abort..." >&2
read


psql -tq -d "$database" -U sergey -f "$(basename "$1")" || fatal "������ ���������� � ��������."

echo "Press Enter..." >&2
read
