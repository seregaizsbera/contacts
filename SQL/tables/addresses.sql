DROP TABLE addresses;

BEGIN;

CREATE TABLE addresses (
    person int4 NOT NULL
                REFERENCES persons(id)
	        ON DELETE RESTRICT
	        ON UPDATE RESTRICT,
    address text NOT NULL
                 CHECK (address != ''),
    PRIMARY KEY (person)
);

COMMENT ON TABLE addresses IS 'Адреса';
COMMENT ON COLUMN addresses.person IS 'Идентификатор личности';
COMMENT ON COLUMN addresses.address IS 'Адрес';

REVOKE ALL ON addresses FROM PUBLIC;
GRANT SELECT, INSERT, UPDATE, DELETE ON addresses TO apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON addresses TO j2eeagent;

COMMIT;
