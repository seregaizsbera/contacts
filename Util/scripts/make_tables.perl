#! /usr/bin/perl
use strict;
$^W = 1;

sub make_table($);

my @tables = (#"birthdays",
              #"call_directions",
              #"call_reports",
              #"call_types",
              #"calls",
              #"calls_pays",
              #"coworkers",
              #"emails",
              #"friends",
              #"gprs",
              #"gprs_urls",
              #"icqs",
              #"msu",
              #"msu_departments",
              #"person_phones",
              #"persons",
              #"phone_types",
              #"phones",
              #"shnip",
              #"supplies",
              #"supply_kinds",
              #"supply_phones",
	      #"relatives",
	      #"addresses",
	      #"properties",
	      #"call_expenses"
	     );
		      
foreach my $table (@tables) {
    print($table,"\n");
    eval {
        make_table($table);
    }; if ($@) {
        my $errorMessage = $@;
        print STDERR "Error making script for table $table:\n";
        print STDERR $errorMessage;
    }
}

sub make_table($) {
    my $table = shift;
    my $sequence = $table . "_id_seq";
    open(FILE, "> $table.sql") || die "Can't open file $table.sql";
    print FILE (<<EOF);
DROP TABLE $table;
DROP SEQUENCE $sequence;

BEGIN;

CREATE SEQUENCE $sequence
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CYCLE;

CREATE TABLE $table (
    id int4 NOT NULL
            DEFAULT nextval('$sequence'::text),
    note text CHECK (note != ''),
    PRIMARY KEY (id)
);

COMMENT ON TABLE $table IS '';
COMMENT ON COLUMN $table.id IS 'Идентификатор ';
COMMENT ON COLUMN $table.note IS 'Примечание';
COMMENT ON SEQUENCE $sequence IS 'Генератор идентификаторов ';

REVOKE ALL ON $table, $sequence FROM PUBLIC;
REVOKE ALL ON $table, $sequence FROM j2eeagent;
REVOKE ALL ON $table, $sequence FROM apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON $table TO j2eeagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON $table TO apacheagent;
GRANT SELECT, UPDATE ON $sequence TO j2eeagent;
GRANT SELECT, UPDATE ON $sequence TO apacheagent;

COMMIT;
EOF
    close(FILE);
}
