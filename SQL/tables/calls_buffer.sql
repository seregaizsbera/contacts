DROP TABLE calls_buffer;
DROP SEQUENCE calls_buffer_id_seq;

BEGIN;

CREATE SEQUENCE calls_buffer_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CYCLE;

CREATE TABLE calls_buffer (
    id int4 NOT NULL
            DEFAULT nextval('calls_buffer_id_seq'::text),
    moment timestamp NOT NULL,
    direction int4 NOT NULL
                   REFERENCES call_directions(id)
		           ON DELETE RESTRICT
		           ON UPDATE RESTRICT,
    phone text CHECK (phone != ''),
    place text CHECK (place != ''),
    type int4 NOT NULL
              REFERENCES call_types(id)
	          ON DELETE RESTRICT
	          ON UPDATE RESTRICT,
    quantity time NOT NULL,
    price numeric NOT NULL,
    note text CHECK (note != ''),
    PRIMARY KEY (id)
);

COMMENT ON TABLE calls_buffer IS 'Промежуточная таблица для хранения информации о звонках';
COMMENT ON COLUMN calls_buffer.id IS 'Идентификатор звонка';
COMMENT ON COLUMN calls_buffer.moment IS 'Время звонка';
COMMENT ON COLUMN calls_buffer.direction IS 'Направление звонка';
COMMENT ON COLUMN calls_buffer.phone IS 'Номер абонента';
COMMENT ON COLUMN calls_buffer.place IS 'Информация о местоположении абонента';
COMMENT ON COLUMN calls_buffer.type IS 'Тип звонка';
COMMENT ON COLUMN calls_buffer.quantity IS 'Длительность звонка';
COMMENT ON COLUMN calls_buffer.price IS 'Стоимость звонка';
COMMENT ON COLUMN calls_buffer.note IS 'Дополнительная информация';
COMMENT ON SEQUENCE calls_buffer_id_seq IS 'Генератор идентификаторов звонков';

REVOKE ALL ON calls_buffer, calls_buffer_id_seq FROM PUBLIC;
REVOKE ALL ON calls_buffer, calls_buffer_id_seq FROM j2eeagent;
REVOKE ALL ON calls_buffer, calls_buffer_id_seq FROM apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON calls_buffer TO apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON calls_buffer TO j2eeagent;
GRANT SELECT, UPDATE ON calls_buffer_id_seq TO j2eeagent;
GRANT SELECT, UPDATE ON calls_buffer_id_seq TO apacheagent;

COMMIT;
