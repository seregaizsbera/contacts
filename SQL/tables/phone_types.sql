DROP TABLE phone_types;

BEGIN;

CREATE TABLE phone_types (
    id int4 NOT NULL,
    name text NOT NULL
              CHECK (name != '')
              UNIQUE,
    PRIMARY KEY (id)
);

COMMENT ON TABLE phone_types IS 'Типы телефонов';
COMMENT ON COLUMN phone_types.id IS 'Идентификатор типа телефона';
COMMENT ON COLUMN phone_types.name IS 'Тип телефона';

REVOKE ALL ON phone_types FROM PUBLIC;
REVOKE ALL ON phone_types FROM j2eeagent;
REVOKE ALL ON phone_types FROM apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON phone_types TO apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON phone_types TO j2eeagent;

COMMIT;
