-- Generated by table.perl script at Thu Oct 30 19:57:28 2003

DROP TABLE genders;

BEGIN;

CREATE TABLE genders (
    id int4 NOT NULL
            PRIMARY KEY,
    name text CHECK (name <> '')
);

COMMENT ON TABLE genders IS '���������� ������������� �����';

COMMENT ON COLUMN genders.id IS '�������� ��������';
COMMENT ON COLUMN genders.name IS '��������� ��������';

REVOKE ALL ON genders FROM PUBLIC;
REVOKE ALL ON genders FROM j2eeagent;
REVOKE ALL ON genders FROM apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON genders TO j2eeagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON genders TO apacheagent;

INSERT INTO genders (id, name) VALUES (0, '�������');
INSERT INTO genders (id, name) VALUES (1, '�������');

COMMIT;