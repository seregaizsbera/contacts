DROP TABLE friends;
DROP SEQUENCE friends_id_seq;

BEGIN;

CREATE SEQUENCE friends_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CYCLE;

CREATE TABLE friends (
    id int4 NOT NULL
            DEFAULT nextval('friends_id_seq'::text),
    note text CHECK (note != ''),
    PRIMARY KEY (id)
);

REVOKE ALL ON friends FROM PUBLIC;
GRANT SELECT, INSERT, UPDATE, DELETE ON friends TO apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON friends TO j2eeagent;

COMMIT;
