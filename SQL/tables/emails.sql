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
    person int4 NOT NULL
                REFERENCES persons(id)
                ON DELETE RESTRICT
    	        ON UPDATE RESTRICT,
    email text NOT NULL
               CHECK (email != ''),
    basic boolean NOT NULL,
    PRIMARY KEY (id)
);

REVOKE ALL ON emails FROM PUBLIC;
GRANT SELECT, INSERT, UPDATE, DELETE ON emails TO apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON emails TO j2eeagent;

COMMIT;
