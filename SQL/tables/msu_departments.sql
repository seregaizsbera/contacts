DROP TABLE msu_departments;
DROP SEQUENCE msu_departments_id_seq;

BEGIN;

CREATE SEQUENCE msu_departments_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CYCLE;

CREATE TABLE msu_departments (
    id int4 NOT NULL
            DEFAULT nextval('msu_departments_id_seq'::text),
    name varchar(12) NOT NULL
                     CHECK (name != '')
                     UNIQUE,
    official_name text NOT NULL
                    CHECK (official_name != '')
                    UNIQUE,
    PRIMARY KEY (id)
);

COMMENT ON TABLE msu_departments IS 'Факультеты МГУ';
COMMENT ON COLUMN msu_departments.id IS 'Идентификатор факультета МГУ';
COMMENT ON COLUMN msu_departments.name IS 'Краткое наименование факультета МГУ';
COMMENT ON COLUMN msu_departments.official_name IS 'Название факультета МГУ';
COMMENT ON SEQUENCE msu_departments_id_seq IS 'Генератор идентификаторов факультетов МГУ';

REVOKE ALL ON msu_departments, msu_departments_id_seq FROM PUBLIC;
REVOKE ALL ON msu_departments, msu_departments_id_seq FROM j2eeagent;
REVOKE ALL ON msu_departments, msu_departments_id_seq FROM apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON msu_departments TO apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON msu_departments TO j2eeagent;
GRANT SELECT, UPDATE ON msu_departments_id_seq TO apacheagent;
GRANT SELECT, UPDATE ON msu_departments_id_seq TO j2eeagent;

COMMIT;
