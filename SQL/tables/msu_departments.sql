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
    short_name varchar(12) NOT NULL
                           CHECK (short_name != '')
                           UNIQUE,
    department text NOT NULL
                    CHECK (department != '')
                    UNIQUE,
    PRIMARY KEY (id)
);

REVOKE ALL ON msu_departments FROM PUBLIC;
GRANT SELECT, INSERT, UPDATE, DELETE ON msu_departments TO apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON msu_departments TO j2eeagent;

COMMIT;
