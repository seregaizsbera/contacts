DROP TABLE call_types;
DROP SEQUENCE call_types_id_seq;

BEGIN;

CREATE SEQUENCE call_types_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CYCLE;

CREATE TABLE call_types (
    id int4 NOT NULL
            DEFAULT nextval('call_types_id_seq'::text),
    type text NOT NULL
              CHECK (type != '')
			  UNIQUE,
    PRIMARY KEY (id)
);

REVOKE ALL ON call_types, call_types_id_seq FROM PUBLIC;
GRANT SELECT, INSERT, UPDATE, DELETE ON call_types TO apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON call_types TO j2eeagent;
GRANT SELECT, UPDATE ON call_types_id_seq TO apacheagent;
GRANT SELECT, UPDATE ON call_types_id_seq TO j2eeagent;

COMMIT;
