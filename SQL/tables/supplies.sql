DROP TABLE supplies;
DROP SEQUENCE supplies_id_seq;

BEGIN;

CREATE SEQUENCE supplies_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CYCLE;

CREATE TABLE supplies (
    id int4 NOT NULL
            DEFAULT nextval('supplies_id_seq'::text),
    note text CHECK (note != ''),
    PRIMARY KEY (id)
);

REVOKE ALL ON supplies FROM PUBLIC;
GRANT SELECT, INSERT, UPDATE, DELETE ON supplies TO apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON supplies TO j2eeagent;

COMMIT;
