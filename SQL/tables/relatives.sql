DROP TABLE relatives;

BEGIN;

CREATE TABLE relatives (
    person int4 NOT NULL
                REFERENCES persons(id)
	        ON DELETE RESTRICT
	        ON UPDATE RESTRICT,
    relationship text NOT NULL
                      CHECK (relationship != ''),
    note text CHECK (note != ''),
    PRIMARY KEY (person)
);

COMMENT ON TABLE relatives IS 'Информация о родственниках';
COMMENT ON COLUMN relatives.person IS 'Идентификатор личности';
COMMENT ON COLUMN relatives.relationship IS 'Степень родства';
COMMENT ON COLUMN relatives.note IS 'Дополнительная информация';

REVOKE ALL ON relatives FROM PUBLIC;
REVOKE ALL ON relatives FROM j2eeagent;
REVOKE ALL ON relatives FROM apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON relatives TO apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON relatives TO j2eeagent;

COMMIT;
