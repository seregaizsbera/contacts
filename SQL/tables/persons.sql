DROP INDEX persons_first_last_index;
DROP TABLE persons;
DROP SEQUENCE persons_id_seq;

BEGIN;

CREATE SEQUENCE persons_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CYCLE;

CREATE TABLE persons (
    id int4 NOT NULL
            DEFAULT nextval('persons_id_seq'::text),
    first text NOT NULL
               CHECK (first != ''),
    middle text CHECK (middle != ''),
    last text NOT NULL
              CHECK (last != ''),
    gender int4 NOT NULL
                REFERENCES genders(id)
                ON DELETE RESTRICT
                ON UPDATE RESTRICT,
    note text CHECK (note != ''),
    insert_time timestamp NOT NULL
                          DEFAULT now(),
    update_time timestamp NOT NULL
                          DEFAULT now(),
    PRIMARY KEY (id)
);

CREATE TRIGGER insert_into_persons
    BEFORE INSERT ON persons
    FOR EACH ROW
    EXECUTE PROCEDURE set_insert_time();

CREATE TRIGGER update_persons
    BEFORE UPDATE ON persons
    FOR EACH ROW
    EXECUTE PROCEDURE set_update_time();

CREATE INDEX persons_first_last_index ON persons(first, last);

COMMENT ON TABLE persons IS '��������� ��� ��������';
COMMENT ON COLUMN persons.id IS '������������� ��������';
COMMENT ON COLUMN persons.first IS '���';
COMMENT ON COLUMN persons.middle IS '��������';
COMMENT ON COLUMN persons.last IS '�������';
COMMENT ON COLUMN persons.gender IS '���';
COMMENT ON COLUMN persons.note IS '�������������� ����������';
COMMENT ON COLUMN persons.insert_time IS '����� ��������� ������';
COMMENT ON COLUMN persons.update_time IS '����� ���������� ���������� ������';
COMMENT ON SEQUENCE persons_id_seq IS '��������� ��������������� ���������';
COMMENT ON INDEX persons_first_last_index IS '����������� ������ �� ����� � �������';

REVOKE ALL ON persons, persons_id_seq FROM PUBLIC;
REVOKE ALL ON persons, persons_id_seq FROM j2eeagent;
REVOKE ALL ON persons, persons_id_seq FROM apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON persons TO apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON persons TO j2eeagent;
GRANT SELECT, UPDATE ON persons_id_seq TO apacheagent;
GRANT SELECT, UPDATE ON persons_id_seq TO j2eeagent;

COMMIT;
