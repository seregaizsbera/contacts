DROP TABLE gprs;
DROP SEQUENCE gprs_id_seq;

BEGIN;

CREATE SEQUENCE gprs_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CYCLE;

CREATE TABLE gprs (
    id int4 NOT NULL
            DEFAULT nextval('gprs_id_seq'::text),
    moment timestamp NOT NULL,
    direction int4 NOT NULL
                   REFERENCES call_directions(id)
		   ON DELETE RESTRICT
		   ON UPDATE RESTRICT,
    url int4 NOT NULL
             REFERENCES gprs_urls(id)
	     ON DELETE RESTRICT
	     ON UPDATE RESTRICT,
    traffic int4 NOT NULL,
    price numeric NOT NULL,
    note text CHECK (note != ''),             
    PRIMARY KEY (id)
);

COMMENT ON TABLE gprs IS 'Информация об использовании GPRS';
COMMENT ON COLUMN gprs.id IS 'Идентификатор случая использования GPRS';
COMMENT ON COLUMN gprs.moment IS 'Время использования GPRS';
COMMENT ON COLUMN gprs.direction IS 'Направление трафика';
COMMENT ON COLUMN gprs.url IS 'Идентификатор адреса точки входа в GPRS';
COMMENT ON COLUMN gprs.traffic IS 'Количество переданных/полученных байт';
COMMENT ON COLUMN gprs.price IS 'Стоимость использования GPRS';
COMMENT ON COLUMN gprs.note IS 'Дополнительная информация';
COMMENT ON SEQUENCE gprs_id_seq IS 'Генератор идентификаторов случаев использования GPRS';

REVOKE ALL ON gprs, gprs_id_seq FROM PUBLIC;
REVOKE ALL ON gprs, gprs_id_seq FROM j2eeagent;
REVOKE ALL ON gprs, gprs_id_seq FROM apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON gprs TO apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON gprs TO j2eeagent;
GRANT SELECT, UPDATE ON calls_id_seq TO apacheagent;
GRANT SELECT, UPDATE ON calls_id_seq TO j2eeagent;

COMMIT;
