DROP TABLE coworkers;

BEGIN;

CREATE TABLE coworkers (
    person int4 NOT NULL
                REFERENCES persons(id)
		        ON DELETE RESTRICT
		        ON UPDATE RESTRICT,
    job text NOT NULL
             CHECK (job != ''),
    description text CHECK (description != ''),
    PRIMARY KEY (person)
);

REVOKE ALL ON coworkers FROM PUBLIC;
GRANT SELECT, INSERT, UPDATE, DELETE ON coworkers TO apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON coworkers TO j2eeagent;

COMMIT;
