DROP TABLE gprs;
DROP SEQUENCE gprs_id_seq;

BEGIN;

CREATE SEQUENCE gprs_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CYCLE;

CREATE TABLE gprs (
    id int4 NOT NULL
            DEFAULT nextval('gprs_id_seq'::text),
    note text CHECK (note != ''),
    PRIMARY KEY (id)
);

REVOKE ALL ON gprs FROM PUBLIC;
GRANT SELECT, INSERT, UPDATE, DELETE ON gprs TO apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON gprs TO j2eeagent;

COMMIT;
