DROP TABLE supply_kinds;
DROP SEQUENCE supply_kinds_id_seq;

BEGIN;

CREATE SEQUENCE supply_kinds_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CYCLE;

CREATE TABLE supply_kinds (
    id int4 NOT NULL
            DEFAULT nextval('supply_kinds_id_seq'::text),
    kind text NOT NULL
              CHECK (kind != '')
              UNIQUE,
    PRIMARY KEY (id)
);

CREATE INDEX supply_kinds_kind_index ON supply_kinds(kind);

COMMENT ON TABLE supply_kinds IS '���� ����������� � ����������� �� ���� ������������';
COMMENT ON COLUMN supply_kinds.id IS '������������� ���� �����������';
COMMENT ON COLUMN supply_kinds.kind IS '���';
COMMENT ON SEQUENCE supply_kinds_id_seq IS '��������� ��������������� ����� �����������';
COMMENT ON INDEX supply_kinds_kind_index IS '����������� ������ ����������� � ����������� �� ���� ������������';

REVOKE ALL ON supply_kinds, supply_kinds_id_seq FROM PUBLIC;
GRANT SELECT, INSERT, UPDATE, DELETE ON supply_kinds TO apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON supply_kinds TO j2eeagent;
GRANT SELECT, UPDATE ON supply_kinds_id_seq TO apacheagent;
GRANT SELECT, UPDATE ON supply_kinds_id_seq TO j2eeagent;

COMMIT;