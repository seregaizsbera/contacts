DROP TABLE calls;
DROP SEQUENCE calls_id_seq;

BEGIN;

CREATE SEQUENCE calls_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CYCLE;

CREATE TABLE calls (
    id int4 NOT NULL
            DEFAULT nextval('calls_id_seq'::text),
    moment timestamp NOT NULL,
    direction int4 NOT NULL
                   REFERENCES call_directions(id)
		           ON DELETE RESTRICT
		           ON UPDATE RESTRICT,
    phone text CHECK (phone != ''),
    place text CHECK (place != ''),
    type int4 NOT NULL
              REFERENCES call_types(id)
	          ON DELETE RESTRICT
	          ON UPDATE RESTRICT,
    quantity time NOT NULL,
    price numeric NOT NULL,
    note text CHECK (note != ''),
    PRIMARY KEY (id)
);

REVOKE ALL ON calls, calls_id_seq FROM PUBLIC;
GRANT SELECT, INSERT, UPDATE, DELETE ON calls TO apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON calls TO j2eeagent;
GRANT SELECT, UPDATE ON calls_id_seq TO apacheagent;
GRANT SELECT, UPDATE ON calls_id_seq TO j2eeagent;

COMMIT;
