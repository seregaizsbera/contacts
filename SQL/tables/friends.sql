DROP TABLE friends;

BEGIN;

CREATE TABLE friends (
    person int4 NOT NULL
                REFERENCES persons(id)
		        ON DELETE RESTRICT
		        ON UPDATE RESTRICT,
    description text CHECK (description != ''),
    PRIMARY KEY (person)
);

COMMENT ON TABLE friends IS '���������� � �������';
COMMENT ON COLUMN friends.person IS '������������� ��������';
COMMENT ON COLUMN friends.description IS '�������������� ����������';

REVOKE ALL ON friends FROM PUBLIC;
REVOKE ALL ON friends FROM j2eeagent;
REVOKE ALL ON friends FROM apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON friends TO apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON friends TO j2eeagent;

COMMIT;
