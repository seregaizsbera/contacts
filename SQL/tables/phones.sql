DROP INDEX phones_phone_index;
DROP TABLE phones;
DROP SEQUENCE phones_id_seq;

BEGIN;

CREATE SEQUENCE phones_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CYCLE;

CREATE TABLE phones (
    id int4 NOT NULL
            DEFAULT nextval('phones_id_seq'::text),
    phone text NOT NULL
               CHECK (phone != ''),
    type int4 NOT NULL
              REFERENCES phone_types(id)
		      ON DELETE RESTRICT
		      ON UPDATE RESTRICT,
    basic boolean NOT NULL,
    PRIMARY KEY (id)
);

CREATE INDEX phones_phone_index ON phones(phone);

REVOKE ALL ON phones, phones_id_seq FROM PUBLIC;
GRANT SELECT, INSERT, UPDATE, DELETE ON phones TO apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON phones TO j2eeagent;
GRANT SELECT, UPDATE ON phones_id_seq TO apacheagent;
GRANT SELECT, UPDATE ON phones_id_seq TO j2eeagent;

COMMIT;
