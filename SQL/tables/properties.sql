DROP TABLE properties;
DROP SEQUENCE properties_id_seq;

BEGIN;

CREATE SEQUENCE properties_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CYCLE;

CREATE TABLE properties (
    id int4 NOT NULL
            DEFAULT nextval('properties_id_seq'::text),
    name text NOT NULL
              UNIQUE
              CHECK (name != ''),
    value text CHECK (value != ''),
    format text NOT NULL
                CHECK (format != ''),
    type text NOT NULL
              CHECK (type != ''),
    parser text NOT NULL
                CHECK (parser != ''),
    description text CHECK (description != ''),
    PRIMARY KEY (id)
);

COMMENT ON TABLE properties IS '��������� ���������';
COMMENT ON COLUMN properties.id IS '������������� ���������� ���������';
COMMENT ON COLUMN properties.name IS '��� ���������� ���������';
COMMENT ON COLUMN properties.value IS '�������� ���������� ���������';
COMMENT ON COLUMN properties.format IS '������ ���������� ��������� � ���� ����������� ���������';
COMMENT ON COLUMN properties.type IS '��� ���� ���������� ��������� � ����� Java';
COMMENT ON COLUMN properties.parser IS '��� Java-������, ������������ �������� ���������� ���������';
COMMENT ON COLUMN properties.description IS '�������� ���������� ���������';
COMMENT ON SEQUENCE properties_id_seq IS '��������� ��������������� ��������� ����������';

REVOKE ALL ON properties, properties_id_seq FROM PUBLIC;
REVOKE ALL ON properties, properties_id_seq FROM j2eeagent;
REVOKE ALL ON properties, properties_id_seq FROM apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON properties TO j2eeagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON properties TO apacheagent;
GRANT SELECT, UPDATE ON properties_id_seq TO j2eeagent;
GRANT SELECT, UPDATE ON properties_id_seq TO apacheagent;

COMMIT;
