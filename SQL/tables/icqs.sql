DROP TABLE icqs;

BEGIN;

CREATE TABLE icqs (
    person int4 NOT NULL
                REFERENCES persons(id)
		        ON DELETE RESTRICT
		        ON UPDATE RESTRICT,
    icq int8 NOT NULL
             UNIQUE,
    alias text NOT NULL
               CHECK (alias != ''),
    PRIMARY KEY (person)
);

REVOKE ALL ON icqs FROM PUBLIC;
GRANT SELECT, INSERT, UPDATE, DELETE ON icqs TO apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON icqs TO j2eeagent;

COMMIT;
