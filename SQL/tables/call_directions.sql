DROP TABLE call_directions;
DROP SEQUENCE call_directions_id_seq;

BEGIN;

CREATE SEQUENCE call_directions_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CYCLE;

CREATE TABLE call_directions (
    id int4 NOT NULL
            DEFAULT nextval('call_directions_id_seq'::text),
    note text CHECK (note != ''),
    PRIMARY KEY (id)
);

REVOKE ALL ON call_directions FROM PUBLIC;
GRANT SELECT, INSERT, UPDATE, DELETE ON call_directions TO apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON call_directions TO j2eeagent;

COMMIT;
