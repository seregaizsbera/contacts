DROP TABLE supply_phones;
DROP SEQUENCE supply_phones_id_seq;

BEGIN;

CREATE SEQUENCE supply_phones_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CYCLE;

CREATE TABLE supply_phones (
    id int4 NOT NULL
            DEFAULT nextval('supply_phones_id_seq'::text),
    note text CHECK (note != ''),
    PRIMARY KEY (id)
);

REVOKE ALL ON supply_phones FROM PUBLIC;
GRANT SELECT, INSERT, UPDATE, DELETE ON supply_phones TO apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON supply_phones TO j2eeagent;

COMMIT;
