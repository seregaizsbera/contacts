DROP TABLE inquiry;

BEGIN;

CREATE TABLE inquiry (
    alias text NOT NULL
               CHECK (alias != ''),
    query text NOT NULL
               CHECK (query != ''),
    scope int4 CHECK (scope in (1, 2, 3, 4)),
    mode int4 NOT NULL
              REFERENCES inquiry_modes(id)
	      ON DELETE RESTRICT
	      ON UPDATE RESTRICT,
    role text CHECK (role != ''),
    description text CHECK (description != ''),
    PRIMARY KEY (alias)
);

COMMENT ON TABLE inquiry IS '�������� ���������� ������ �������';
COMMENT ON COLUMN inquiry.alias IS '��������� �����������';
COMMENT ON COLUMN inquiry.query IS '������ � ���� ������, ������������ ������ �����������';
COMMENT ON COLUMN inquiry.scope IS '������� ������, ���� ����� ��������� ����������';
COMMENT ON COLUMN inquiry.mode IS '� ����� ���� ����� �������� ���������� ������';
COMMENT ON COLUMN inquiry.role IS '����, � ������� ������ ���������� ������������ ��� ������� � �����������';
COMMENT ON COLUMN inquiry.description IS '�������� �����������';

GRANT SELECT, INSERT, UPDATE, DELETE ON inquiry TO j2eeagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON inquiry TO apacheagent;

COMMIT;
