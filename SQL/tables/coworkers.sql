DROP TABLE coworkers;
DROP SEQUENCE coworkers_id_seq;

BEGIN;

CREATE SEQUENCE coworkers_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CYCLE;

CREATE TABLE coworkers (
    id int4 NOT NULL
            DEFAULT nextval('coworkers_id_seq'::text),
    note text CHECK (note != ''),
    PRIMARY KEY (id)
);

REVOKE ALL ON coworkers FROM PUBLIC;
GRANT SELECT, INSERT, UPDATE, DELETE ON coworkers TO apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON coworkers TO j2eeagent;

COMMIT;
