DROP TABLE gprs_urls;
DROP SEQUENCE gprs_urls_id_seq;

BEGIN;

CREATE SEQUENCE gprs_urls_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CYCLE;

CREATE TABLE gprs_urls (
    id int4 NOT NULL
            DEFAULT nextval('gprs_urls_id_seq'::text),
    note text CHECK (note != ''),
    PRIMARY KEY (id)
);

REVOKE ALL ON gprs_urls FROM PUBLIC;
GRANT SELECT, INSERT, UPDATE, DELETE ON gprs_urls TO apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON gprs_urls TO j2eeagent;

COMMIT;