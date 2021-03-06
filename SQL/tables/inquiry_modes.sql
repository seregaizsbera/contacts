-- Generated by nsi.perl script at Mon Sep 20 15:02:57 2004

DROP TABLE inquiry_modes;

BEGIN;

CREATE TABLE inquiry_modes (
    id int4 NOT NULL PRIMARY KEY,
    name text NOT NULL UNIQUE CHECK (name <> '')
);

COMMENT ON TABLE inquiry_modes IS '������ ��������� ���������� ������ �������';

COMMENT ON COLUMN inquiry_modes.id IS '�������� ��������';
COMMENT ON COLUMN inquiry_modes.name IS '��������� ��������';

REVOKE ALL ON inquiry_modes FROM PUBLIC;
REVOKE ALL ON inquiry_modes FROM j2eeagent;
REVOKE ALL ON inquiry_modes FROM apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON inquiry_modes TO j2eeagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON inquiry_modes TO apacheagent;

INSERT INTO inquiry_modes (id, name) VALUES (1, '���������');
INSERT INTO inquiry_modes (id, name) VALUES (2, '�����������');

COMMIT;
