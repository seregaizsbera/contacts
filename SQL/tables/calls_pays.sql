DROP TABLE calls_pays;
DROP SEQUENCE calls_pays_id_seq;

BEGIN;

CREATE SEQUENCE calls_pays_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CYCLE;

CREATE TABLE calls_pays (
    id int4 NOT NULL
            DEFAULT nextval('calls_pays_id_seq'::text),
    pay_day date NOT NULL
                 CHECK (date_le(pay_day, date(now()))),
    amount numeric NOT NULL,
    course numeric,
    amount_in_abstract_units numeric NOT NULL,
    note text CHECK (note != ''),
    PRIMARY KEY (id)
);

REVOKE ALL ON calls_pays, calls_pays_id_seq FROM PUBLIC;
GRANT SELECT, INSERT, UPDATE, DELETE ON calls_pays TO apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON calls_pays TO j2eeagent;
GRANT SELECT, UPDATE ON calls_pays_id_seq TO apacheagent;
GRANT SELECT, UPDATE ON calls_pays_id_seq TO j2eeagent;

COMMIT;
