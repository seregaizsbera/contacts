DROP TABLE emails;
DROP SEQUENCE emails_id_seq;

BEGIN;

CREATE SEQUENCE emails_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CYCLE;

CREATE TABLE emails (
    id int4 NOT NULL
            DEFAULT nextval('emails_id_seq'::text),
    note text CHECK (note != ''),
    PRIMARY KEY (id)
);

REVOKE ALL ON emails FROM PUBLIC;
GRANT SELECT, INSERT, UPDATE, DELETE ON emails TO apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON emails TO j2eeagent;

COMMIT;
