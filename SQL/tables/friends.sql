DROP TABLE friends;

BEGIN;

CREATE TABLE friends (
    person int4 NOT NULL
                REFERENCES persons(id)
		        ON DELETE RESTRICT
		        ON UPDATE RESTRICT,
    description text CHECK (description != ''),
    PRIMARY KEY (person)
);

REVOKE ALL ON friends FROM PUBLIC;
GRANT SELECT, INSERT, UPDATE, DELETE ON friends TO apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON friends TO j2eeagent;

COMMIT;
