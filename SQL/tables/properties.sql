DROP TABLE properties;

BEGIN;

CREATE TABLE properties (
    name text NOT NULL
              CHECK (name != ''),
    value text CHECK (value != ''),
    format text NOT NULL
                CHECK (format != ''),
    type text NOT NULL
              CHECK (type != ''),
    maker text CHECK (maker != ''),
    note text CHECK (note != ''),
    PRIMARY KEY (name)
);

COMMENT ON TABLE properties IS 'Системные параметры';
COMMENT ON COLUMN properties.name IS 'Имя системного параметра';
COMMENT ON COLUMN properties.value IS 'Значение системного параметра';
COMMENT ON COLUMN properties.format IS 'Формат системного параметра';
COMMENT ON COLUMN properties.type IS 'Имя типа системного параметра в языке Java';
COMMENT ON COLUMN properties.maker IS 'Имя Java-класса, разбирающего значение системного параметра';
COMMENT ON COLUMN properties.note IS 'Дополнительная информация';

REVOKE ALL ON properties FROM PUBLIC;
REVOKE ALL ON properties FROM j2eeagent;
REVOKE ALL ON properties FROM apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON properties TO j2eeagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON properties TO apacheagent;

COMMIT;
