DROP TABLE call_reports;
DROP SEQUENCE call_reports_id_seq;

BEGIN;

CREATE SEQUENCE call_reports_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CYCLE;

CREATE TABLE call_reports (
    id int4 NOT NULL
            DEFAULT nextval('call_reports_id_seq'::text),
    first_day date NOT NULL
                   CHECK (date_le(first_day, date(now()))),
    last_day date NOT NULL
                  CHECK (date_le(last_day, date(now()))),
    arrival_day date NOT NULL
                     CHECK (date_le(arrival_day, date(now()))),
    process_day date NOT NULL
                     CHECK (date_le(process_day, date(now()))),
    pure_period_price numeric NOT NULL,
    note text CHECK (note != ''),
    PRIMARY KEY (id)
);

COMMENT ON TABLE call_reports IS 'Детализированные счета об оплате мобильной связи';
COMMENT ON COLUMN call_reports.id IS 'Идентификатор счета';
COMMENT ON COLUMN call_reports.first_day IS 'Первый день, включенный в счет';
COMMENT ON COLUMN call_reports.last_day IS 'Последний день, включенный в счет';
COMMENT ON COLUMN call_reports.arrival_day IS 'День доставки счета';
COMMENT ON COLUMN call_reports.process_day IS 'День загрузки счета';
COMMENT ON COLUMN call_reports.pure_period_price IS 'Полная стоимость услуг мобильный связи за период, включенный в счет';
COMMENT ON COLUMN call_reports.note IS 'Примечание';
COMMENT ON SEQUENCE call_reports_id_seq IS 'Генератор идентификаторов счетов';

REVOKE ALL ON call_reports, call_reports_id_seq FROM PUBLIC;
REVOKE ALL ON call_reports, call_reports_id_seq FROM j2eeagent;
REVOKE ALL ON call_reports, call_reports_id_seq FROM apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON call_reports TO apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON call_reports TO j2eeagent;
GRANT SELECT, UPDATE ON call_reports_id_seq TO apacheagent;
GRANT SELECT, UPDATE ON call_reports_id_seq TO j2eeagent;

COMMIT;
