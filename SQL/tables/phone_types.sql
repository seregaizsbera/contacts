DROP TABLE phone_types;
DROP SEQUENCE phone_types_id_seq;

BEGIN;

CREATE SEQUENCE phone_types_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CYCLE;

CREATE TABLE phone_types (
    id int4 NOT NULL
            DEFAULT nextval('phone_types_id_seq'::text),
    name text NOT NULL
              CHECK (name != '')
              UNIQUE,
    PRIMARY KEY (id)
);

COMMENT ON TABLE phone_types IS 'Типы телефонов';
COMMENT ON COLUMN phone_types.id IS 'Идентификатор типа телефона';
COMMENT ON COLUMN phone_types.name IS 'Тип телефона';
COMMENT ON SEQUENCE phone_types_id_seq IS 'Генератор идентификаторов типов телефонов';

REVOKE ALL ON phone_types, phone_types_id_seq FROM PUBLIC;
REVOKE ALL ON phone_types, phone_types_id_seq FROM j2eeagent;
REVOKE ALL ON phone_types, phone_types_id_seq FROM apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON phone_types TO apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON phone_types TO j2eeagent;
GRANT SELECT, UPDATE ON phone_types_id_seq TO apacheagent;
GRANT SELECT, UPDATE ON phone_types_id_seq TO j2eeagent;

COMMIT;
