DROP TABLE calls;
DROP SEQUENCE calls_id_seq;

BEGIN;

CREATE SEQUENCE calls_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CYCLE;

CREATE TABLE calls (
    id int4 NOT NULL
            DEFAULT nextval('calls_id_seq'::text),
    note text CHECK (note != ''),
    PRIMARY KEY (id)
);

REVOKE ALL ON calls FROM PUBLIC;
GRANT SELECT, INSERT, UPDATE, DELETE ON calls TO apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON calls TO j2eeagent;

COMMIT;
