DROP TABLE queries;
DROP SEQUENCE queries_id_seq;

BEGIN;

CREATE SEQUENCE queries_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CYCLE;

CREATE TABLE queries (
    id int4 NOT NULL
            DEFAULT nextval('queries_id_seq'::text),
    sql text NOT NULL
             UNIQUE
             CHECK (sql != ''),
    PRIMARY KEY (id)
);

COMMENT ON TABLE queries IS 'Выполненные SQL-запросы';
COMMENT ON COLUMN queries.id IS 'Идентификатор SQL-запроса';
COMMENT ON COLUMN queries.sql IS 'Текст SQL-запроса';
COMMENT ON SEQUENCE queries_id_seq IS 'Генератор идентификаторов SQL-запросов';

REVOKE ALL ON queries, queries_id_seq FROM PUBLIC;
REVOKE ALL ON queries, queries_id_seq FROM j2eeagent;
REVOKE ALL ON queries, queries_id_seq FROM apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON queries TO j2eeagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON queries TO apacheagent;
GRANT SELECT, UPDATE ON queries_id_seq TO j2eeagent;
GRANT SELECT, UPDATE ON queries_id_seq TO apacheagent;

COMMIT;
