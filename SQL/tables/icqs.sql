DROP TABLE icqs;
DROP SEQUENCE icqs_id_seq;

BEGIN;

CREATE SEQUENCE icqs_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CYCLE;

CREATE TABLE icqs (
    id int4 NOT NULL
            DEFAULT nextval('icqs_id_seq'::text),
    note text CHECK (note != ''),
    PRIMARY KEY (id)
);

REVOKE ALL ON icqs FROM PUBLIC;
GRANT SELECT, INSERT, UPDATE, DELETE ON icqs TO apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON icqs TO j2eeagent;

COMMIT;
