DROP INDEX persons_first_second_index;
DROP TABLE persons;
DROP SEQUENCE persons_id_seq;

BEGIN;

CREATE SEQUENCE persons_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CYCLE;

CREATE TABLE persons (
    id int4 NOT NULL
            DEFAULT nextval('persons_id_seq'::text),
    first text NOT NULL
               CHECK (first != ''),
    second text NOT NULL
                CHECK (second != ''),
    last text CHECK (last != ''),
    address text CHECK (address != ''),
    note text CHECK (note != ''),
    PRIMARY KEY (id)
);

CREATE INDEX persons_first_second_index ON persons(first, second);

REVOKE ALL ON persons, persons_id_seq FROM PUBLIC;
GRANT SELECT, INSERT, UPDATE, DELETE ON persons TO apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON persons TO j2eeagent;
GRANT SELECT, UPDATE ON persons_id_seq TO apacheagent;
GRANT SELECT, UPDATE ON persons_id_seq TO j2eeagent;

COMMIT;
