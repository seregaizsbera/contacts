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
    description text CHECK (description != ''),
    PRIMARY KEY (person)
);

COMMENT ON TABLE msu IS '����������� � ���������, ������� ��������� � ���';
COMMENT ON COLUMN msu.person IS '������������� ��������';
COMMENT ON COLUMN msu.graduate IS '���� ����������� (���)';
COMMENT ON COLUMN msu.department IS '������������� ����������';
COMMENT ON COLUMN msu.hospice IS '������� ���������� � ���������';
COMMENT ON COLUMN msu.subfaculty IS '�������';
COMMENT ON COLUMN msu.description IS '�������������� ����������';

REVOKE ALL ON msu FROM PUBLIC;
REVOKE ALL ON msu FROM j2eeagent;
REVOKE ALL ON msu FROM apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON msu TO apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON msu TO j2eeagent;

COMMIT;
