DROP TABLE supply_property_forms;

BEGIN;

CREATE TABLE supply_property_forms (
    id text NOT NULL PRIMARY KEY CHECK (id <>''),
    name text NOT NULL UNIQUE CHECK (name <> '')
);

COMMENT ON TABLE supply_property_forms IS '����� ������������� �����������';

COMMENT ON COLUMN supply_property_forms.id IS '������������';
COMMENT ON COLUMN supply_property_forms.name IS '�����������';

REVOKE ALL ON supply_property_forms FROM PUBLIC;
REVOKE ALL ON supply_property_forms FROM j2eeagent;
REVOKE ALL ON supply_property_forms FROM apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON supply_property_forms TO j2eeagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON supply_property_forms TO apacheagent;

INSERT INTO supply_property_forms (id, name) VALUES ('���', '�������� � ������������ ����������������');
INSERT INTO supply_property_forms (id, name) VALUES ('���', '�������� ����������� ��������');
INSERT INTO supply_property_forms (id, name) VALUES ('���', '�������� ����������� ��������');
INSERT INTO supply_property_forms (id, name) VALUES ('���', '������������� ��������� �����������');
INSERT INTO supply_property_forms (id, name) VALUES ('���', '��������������� ��������� �����������');

COMMIT;
