DROP TABLE msu;

BEGIN;

CREATE TABLE msu (
    person int4 NOT NULL
                REFERENCES persons(id)
		        ON DELETE RESTRICT
		        ON UPDATE RESTRICT,
    graduate date NOT NULL,
    department int NOT NULL
                   REFERENCES msu_departments(id)
				   ON DELETE RESTRICT
				   ON UPDATE RESTRICT,
    hospice boolean NOT NULL,
    subfaculty text CHECK (subfaculty != ''),
    PRIMARY KEY (person)
);

REVOKE ALL ON msu FROM PUBLIC;
GRANT SELECT, INSERT, UPDATE, DELETE ON msu TO apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON msu TO j2eeagent;

COMMIT;
