DROP TABLE icqs;

BEGIN;

CREATE TABLE icqs (
    person int4 NOT NULL
                REFERENCES persons(id)
		        ON DELETE RESTRICT
		        ON UPDATE RESTRICT,
    icq int8 NOT NULL
             UNIQUE,
    nickname text NOT NULL
                  CHECK (nickname != ''),
    PRIMARY KEY (person)
);

COMMENT ON TABLE icqs IS 'Информация о пользователях ICQ';
COMMENT ON COLUMN icqs.person IS 'Идентификатор личности';
COMMENT ON COLUMN icqs.icq IS 'Уникальный идентификатор пользователя ICQ';
COMMENT ON COLUMN icqs.nickname IS 'Псевдоним пользователя ICQ';

REVOKE ALL ON icqs FROM PUBLIC;
GRANT SELECT, INSERT, UPDATE, DELETE ON icqs TO apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON icqs TO j2eeagent;

COMMIT;
