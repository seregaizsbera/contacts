DROP TABLE supply_kinds;

BEGIN;
CREATE TABLE supply_kinds (
    id int4 NOT NULL,
    name text NOT NULL
              CHECK (name != '')
              UNIQUE,
    PRIMARY KEY (id)
);

CREATE INDEX supply_kinds_kind_index ON supply_kinds(name);

COMMENT ON TABLE supply_kinds IS '���� ����������� � ����������� �� ���� ������������';
COMMENT ON COLUMN supply_kinds.id IS '������������� ���� �����������';
COMMENT ON COLUMN supply_kinds.name IS '���';
COMMENT ON INDEX supply_kinds_kind_index IS '����������� ������ ����������� � ����������� �� ���� ������������';

REVOKE ALL ON supply_kinds FROM PUBLIC;
REVOKE ALL ON supply_kinds FROM j2eeagent;
REVOKE ALL ON supply_kinds FROM apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON supply_kinds TO apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON supply_kinds TO j2eeagent;

COMMIT;
