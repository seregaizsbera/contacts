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
    kind int4 NOT NULL
              REFERENCES supply_kinds(id)
              ON DELETE RESTRICT
              ON UPDATE RESTRICT,
    address text CHECK (address != ''),
    url text CHECK (url != ''),
    email text CHECK (url != ''),
    important boolean NOT NULL,
    note text CHECK (note != ''),
    PRIMARY KEY (id)
);

CREATE INDEX supplies_name_index ON supplies(name);
CREATE INDEX supplies_kind_index ON supplies(kind);

COMMENT ON TABLE supplies IS 'Известные мне предприятия и организации';
COMMENT ON COLUMN supplies.id IS 'Идентификатор организации';
COMMENT ON COLUMN supplies.name IS 'Название организации';
COMMENT ON COLUMN supplies.kind IS 'Идентификатор рода деятельности';
COMMENT ON COLUMN supplies.address IS 'Адрес';
COMMENT ON COLUMN supplies.url IS 'Адрес веб-сайта';
COMMENT ON COLUMN supplies.email IS 'Адрес электронной почты';
COMMENT ON COLUMN supplies.important IS 'Признак важности';
COMMENT ON COLUMN supplies.note IS 'Примечание';
COMMENT ON SEQUENCE supplies_id_seq IS 'Генератор идентификаторов организаций';
COMMENT ON INDEX supplies_name_index IS 'Оптимизация поиска по названию';
COMMENT ON INDEX supplies_kind_index IS 'Оптимизация поиска по роду деятельности';

REVOKE ALL ON supplies, supplies_id_seq FROM PUBLIC;
GRANT SELECT, INSERT, UPDATE, DELETE ON supplies TO apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON supplies TO j2eeagent;
GRANT SELECT, UPDATE ON supplies_id_seq TO apacheagent;
GRANT SELECT, UPDATE ON supplies_id_seq TO j2eeagent;

COMMIT;
