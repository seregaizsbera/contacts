DROP TABLE shnip;
DROP SEQUENCE shnip_id_seq;

BEGIN;

CREATE SEQUENCE shnip_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CYCLE;

CREATE TABLE shnip (
    id int4 NOT NULL
            DEFAULT nextval('shnip_id_seq'::text),
    note text CHECK (note != ''),
    PRIMARY KEY (id)
);

REVOKE ALL ON shnip FROM PUBLIC;
GRANT SELECT, INSERT, UPDATE, DELETE ON shnip TO apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON shnip TO j2eeagent;

COMMIT;
