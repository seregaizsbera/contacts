DROP TABLE msu;
DROP SEQUENCE msu_id_seq;

BEGIN;

CREATE SEQUENCE msu_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CYCLE;

CREATE TABLE msu (
    id int4 NOT NULL
            DEFAULT nextval('msu_id_seq'::text),
    note text CHECK (note != ''),
    PRIMARY KEY (id)
);

REVOKE ALL ON msu FROM PUBLIC;
GRANT SELECT, INSERT, UPDATE, DELETE ON msu TO apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON msu TO j2eeagent;

COMMIT;
