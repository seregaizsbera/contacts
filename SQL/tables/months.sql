-- Generated by nsi.perl script at Sat Dec 27 17:01:15 2003

DROP TABLE months;

BEGIN;

CREATE TABLE months (
    id int4 NOT NULL PRIMARY KEY,
    name text NOT NULL UNIQUE CHECK (name <> '')
);

COMMENT ON TABLE months IS '�������� �������';

COMMENT ON COLUMN months.id IS '�������� ��������';
COMMENT ON COLUMN months.name IS '��������� ��������';

REVOKE ALL ON months FROM PUBLIC;
REVOKE ALL ON months FROM j2eeagent;
REVOKE ALL ON months FROM apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON months TO j2eeagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON months TO apacheagent;

INSERT INTO months (id, name) VALUES (1, '������');
INSERT INTO months (id, name) VALUES (2, '�������');
INSERT INTO months (id, name) VALUES (3, '����');
INSERT INTO months (id, name) VALUES (4, '������');
INSERT INTO months (id, name) VALUES (5, '���');
INSERT INTO months (id, name) VALUES (6, '����');
INSERT INTO months (id, name) VALUES (7, '����');
INSERT INTO months (id, name) VALUES (8, '������');
INSERT INTO months (id, name) VALUES (9, '��������');
INSERT INTO months (id, name) VALUES (10, '�������');
INSERT INTO months (id, name) VALUES (11, '������');
INSERT INTO months (id, name) VALUES (12, '�������');

COMMIT;
