DROP TABLE phone_types;
DROP SEQUENCE phone_types_id_seq;

BEGIN;

CREATE SEQUENCE phone_types_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CYCLE;

CREATE TABLE phone_types (
    id int4 NOT NULL
            DEFAULT nextval('phone_types_id_seq'::text),
    type text NOT NULL
              CHECK (type != '')
              UNIQUE,
    PRIMARY KEY (id)
);

REVOKE ALL ON phone_types FROM PUBLIC;
GRANT SELECT, INSERT, UPDATE, DELETE ON phone_types TO apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON phone_types TO j2eeagent;

COMMIT;
