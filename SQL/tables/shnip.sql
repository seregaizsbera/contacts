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

COMMENT ON TABLE shnip IS '����������� � ���������, ������� ��������� � �����';
COMMENT ON COLUMN shnip.person IS '������������� ��������';
COMMENT ON COLUMN shnip.graduate IS '���� ������� (���)';
COMMENT ON COLUMN shnip.form_letter IS '����� �������� ������';
COMMENT ON COLUMN shnip.form_leader IS '������������� ��������� ������������';
COMMENT ON COLUMN shnip.description IS '�������������� ����������';

REVOKE ALL ON shnip FROM PUBLIC;
REVOKE ALL ON shnip FROM j2eeagent;
REVOKE ALL ON shnip FROM apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON shnip TO apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON shnip TO j2eeagent;

COMMIT;
