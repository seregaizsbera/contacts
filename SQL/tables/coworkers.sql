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

COMMENT ON TABLE coworkers IS '���������� � �����������';
COMMENT ON COLUMN coworkers.person IS '������������� ��������';
COMMENT ON COLUMN coworkers.job IS '���������� � ����� ������';
COMMENT ON COLUMN coworkers.description IS '�������������� ����������';

REVOKE ALL ON coworkers FROM PUBLIC;
REVOKE ALL ON coworkers FROM j2eeagent;
REVOKE ALL ON coworkers FROM apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON coworkers TO apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON coworkers TO j2eeagent;

COMMIT;
