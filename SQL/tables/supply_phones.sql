DROP TABLE supply_phones;

BEGIN;

CREATE TABLE supply_phones (
    supply int4 NOT NULL
                REFERENCES supplies(id)
                ON DELETE RESTRICT
                ON UPDATE RESTRICT,
    phone int4 NOT NULL
               REFERENCES phones(id)
               ON DELETE RESTRICT
               ON UPDATE RESTRICT,
    PRIMARY KEY (supply, phone)
);

REVOKE ALL ON supply_phones FROM PUBLIC;
GRANT SELECT, INSERT, UPDATE, DELETE ON supply_phones TO apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON supply_phones TO j2eeagent;

COMMIT;
