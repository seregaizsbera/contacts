DROP TABLE supplies;
DROP SEQUENCE supplies_id_seq;

BEGIN;

CREATE SEQUENCE supplies_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CYCLE;

CREATE TABLE supplies (
    id int4 NOT NULL
            DEFAULT nextval('supplies_id_seq'::text),
    name text NOT NULL
              CHECK (name != ''),
    short_name text CHECK (short_name != ''),
    parent_name text CHECK (parent_name != ''),
    kind int4 NOT NULL
              REFERENCES supply_kinds(id)
              ON DELETE RESTRICT
              ON UPDATE RESTRICT,
    address text CHECK (address != ''),
    url text CHECK (url != ''),
    inn text CHECK (inn != ''),
    kpp text CHECK (kpp != ''),
    ogrn text CHECK (ogrn != ''),
    metro text CHECK (metro != ''),
    important boolean NOT NULL,
    property_form text CHECK (property_form != '')
                       REFERENCES supply_property_forms(id)
		       ON DELETE RESTRICT
		       ON UPDATE RESTRICT,
    holding boolean NOT NULL,
    note text CHECK (note != ''),
    insert_time timestamp NOT NULL
                          DEFAULT now(),
    update_time timestamp NOT NULL
                          DEFAULT now(),
    PRIMARY KEY (id)
);

CREATE TRIGGER insert_into_supplies
    BEFORE INSERT ON supplies
    FOR EACH ROW
    EXECUTE PROCEDURE set_insert_time();

CREATE TRIGGER update_supplies
    BEFORE UPDATE ON supplies
    FOR EACH ROW
    EXECUTE PROCEDURE set_update_time();

CREATE INDEX supplies_name_index ON supplies(name);
CREATE INDEX supplies_kind_index ON supplies(kind);

COMMENT ON TABLE supplies IS '��������� ��� ����������� � �����������';
COMMENT ON COLUMN supplies.id IS '������������� �����������';
COMMENT ON COLUMN supplies.name IS '�������� �����������';
COMMENT ON COLUMN supplies.short_name IS '������� �������� �����������';
COMMENT ON COLUMN supplies.parent_name IS '�������� �������� �����������';
COMMENT ON COLUMN supplies.kind IS '������������� ���� ������������';
COMMENT ON COLUMN supplies.address IS '�����';
COMMENT ON COLUMN supplies.url IS '����� ���-�����';
COMMENT ON COLUMN supplies.inn IS '�������������� ����� �����������������';
COMMENT ON COLUMN supplies.kpp IS '��� ������� ���������� �� ���� � ��������� �������';
COMMENT ON COLUMN supplies.ogrn IS '������������������� ��������������� �����';
COMMENT ON COLUMN supplies.metro IS '��������� ������� �����';
COMMENT ON COLUMN supplies.important IS '������� ��������';
COMMENT ON COLUMN supplies.property_form IS '����� �������������';
COMMENT ON COLUMN supplies.holding IS '������� �������� ����������� ��������';
COMMENT ON COLUMN supplies.note IS '�������������� ����������';
COMMENT ON COLUMN supplies.insert_time IS '����� ��������� ������';
COMMENT ON COLUMN supplies.update_time IS '����� ���������� ���������� ������';
COMMENT ON SEQUENCE supplies_id_seq IS '��������� ��������������� �����������';
COMMENT ON INDEX supplies_name_index IS '����������� ������ �� ��������';
COMMENT ON INDEX supplies_kind_index IS '����������� ������ �� ���� ������������';

REVOKE ALL ON supplies, supplies_id_seq FROM PUBLIC;
REVOKE ALL ON supplies, supplies_id_seq FROM j2eeagent;
REVOKE ALL ON supplies, supplies_id_seq FROM apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON supplies TO apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON supplies TO j2eeagent;
GRANT SELECT, UPDATE ON supplies_id_seq TO apacheagent;
GRANT SELECT, UPDATE ON supplies_id_seq TO j2eeagent;

COMMIT;
