DROP TABLE inquiry;

BEGIN;

CREATE TABLE inquiry (
    alias text NOT NULL
               CHECK (alias != ''),
    query text NOT NULL
               CHECK (query != ''),
    scope int4 CHECK (scope in (1, 2, 3, 4)),
    mode int4 NOT NULL
              REFERENCES inquiry_modes(id)
	      ON DELETE RESTRICT
	      ON UPDATE RESTRICT,
    role text CHECK (role != ''),
    description text CHECK (description != ''),
    PRIMARY KEY (alias)
);

COMMENT ON TABLE inquiry IS 'Описания справочных данных системы';
COMMENT ON COLUMN inquiry.alias IS 'Псевдоним справочника';
COMMENT ON COLUMN inquiry.query IS 'Запрос к базе данных, возвращающий записи справочника';
COMMENT ON COLUMN inquiry.scope IS 'Область данных, куда нужно поместить справочник';
COMMENT ON COLUMN inquiry.mode IS 'В каком виде нужно получить справочные данные';
COMMENT ON COLUMN inquiry.role IS 'Роль, в которой должен находиться пользователь для доступа к справочнику';
COMMENT ON COLUMN inquiry.description IS 'Описание справочника';

GRANT SELECT, INSERT, UPDATE, DELETE ON inquiry TO j2eeagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON inquiry TO apacheagent;

COMMIT;
