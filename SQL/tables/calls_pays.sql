DROP TABLE calls_pays;
DROP SEQUENCE calls_pays_id_seq;

BEGIN;

CREATE SEQUENCE calls_pays_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CYCLE;

CREATE TABLE calls_pays (
    id int4 NOT NULL
            DEFAULT nextval('calls_pays_id_seq'::text),
    pay_day date NOT NULL
                 CHECK (date_le(pay_day, date(now()))),
    amount numeric NOT NULL,
    course numeric,
    amount_in_abstract_units numeric NOT NULL,
    note text CHECK (note != ''),
    PRIMARY KEY (id)
);

COMMENT ON TABLE calls_pays IS 'Информация о платежах за услуги мобильной связи';
COMMENT ON COLUMN calls_pays.id IS 'Идентификатор платежа';
COMMENT ON COLUMN calls_pays.pay_day IS 'Дата платежа';
COMMENT ON COLUMN calls_pays.amount IS 'Размер платежа в рублях';
COMMENT ON COLUMN calls_pays.course IS 'Размер условной единицы на момент платежа';
COMMENT ON COLUMN calls_pays.amount_in_abstract_units IS 'Размер платежа в условных единицах';
COMMENT ON COLUMN calls_pays.note IS 'Примечание';
COMMENT ON SEQUENCE calls_pays_id_seq IS 'Генератор идентификаторов платежей';

REVOKE ALL ON calls_pays, calls_pays_id_seq FROM PUBLIC;
GRANT SELECT, INSERT, UPDATE, DELETE ON calls_pays TO apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON calls_pays TO j2eeagent;
GRANT SELECT, UPDATE ON calls_pays_id_seq TO apacheagent;
GRANT SELECT, UPDATE ON calls_pays_id_seq TO j2eeagent;

COMMIT;
