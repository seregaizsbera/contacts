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

COMMENT ON TABLE relatives IS '���������� � �������������';
COMMENT ON COLUMN relatives.person IS '������������� ��������';
COMMENT ON COLUMN relatives.relationship IS '������� �������';
COMMENT ON COLUMN relatives.note IS '�������������� ����������';

REVOKE ALL ON relatives FROM PUBLIC;
REVOKE ALL ON relatives FROM j2eeagent;
REVOKE ALL ON relatives FROM apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON relatives TO apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON relatives TO j2eeagent;

COMMIT;
