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
    name text NOT NULL
              CHECK (name != '')
              UNIQUE,
    PRIMARY KEY (id)
);

CREATE INDEX supply_kinds_kind_index ON supply_kinds(name);

COMMENT ON TABLE supply_kinds IS 'Виды предприятий и организаций по роду деятельности';
COMMENT ON COLUMN supply_kinds.id IS 'Идентификатор вида организации';
COMMENT ON COLUMN supply_kinds.name IS 'Имя';
COMMENT ON SEQUENCE supply_kinds_id_seq IS 'Генератор идентификаторов видов организаций';
COMMENT ON INDEX supply_kinds_kind_index IS 'Оптимизация поиска предприятий и орагнизаций по роду деятельности';

REVOKE ALL ON supply_kinds, supply_kinds_id_seq FROM PUBLIC;
REVOKE ALL ON supply_kinds, supply_kinds_id_seq FROM j2eeagent;
REVOKE ALL ON supply_kinds, supply_kinds_id_seq FROM apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON supply_kinds TO apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON supply_kinds TO j2eeagent;
GRANT SELECT, UPDATE ON supply_kinds_id_seq TO apacheagent;
GRANT SELECT, UPDATE ON supply_kinds_id_seq TO j2eeagent;

COMMIT;
