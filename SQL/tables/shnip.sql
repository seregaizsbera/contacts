DROP TABLE shnip;

BEGIN;

CREATE TABLE shnip (
    person int4 NOT NULL
                REFERENCES persons(id)
	        ON DELETE RESTRICT
	        ON UPDATE RESTRICT,
    graduate date,
    form_letter text CHECK (form_letter != ''),
    form_leader int4 REFERENCES shnip(person)
				     ON DELETE SET NULL
				     ON UPDATE SET NULL,
    description text CHECK (description != ''),
    PRIMARY KEY (person)
);

REVOKE ALL ON shnip FROM PUBLIC;
GRANT SELECT, INSERT, UPDATE, DELETE ON shnip TO apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON shnip TO j2eeagent;

COMMIT;
