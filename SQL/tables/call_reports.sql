DROP TABLE call_reports;
DROP SEQUENCE call_reports_id_seq;

BEGIN;

CREATE SEQUENCE call_reports_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CYCLE;

CREATE TABLE call_reports (
    id int4 NOT NULL
            DEFAULT nextval('call_reports_id_seq'::text),
    note text CHECK (note != ''),
    PRIMARY KEY (id)
);

REVOKE ALL ON call_reports FROM PUBLIC;
GRANT SELECT, INSERT, UPDATE, DELETE ON call_reports TO apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON call_reports TO j2eeagent;

COMMIT;
