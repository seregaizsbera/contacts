DROP TABLE call_pays;
DROP SEQUENCE call_pays_id_seq;

BEGIN;

CREATE SEQUENCE call_pays_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CYCLE;

CREATE TABLE call_pays (
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

COMMENT ON TABLE call_pays IS '���������� � �������� �� ������ ��������� �����';
COMMENT ON COLUMN call_pays.id IS '������������� �������';
COMMENT ON COLUMN call_pays.pay_day IS '���� �������';
COMMENT ON COLUMN call_pays.amount IS '������ ������� � ������';
COMMENT ON COLUMN call_pays.course IS '������ �������� ������� �� ������ �������';
COMMENT ON COLUMN call_pays.amount_in_abstract_units IS '������ ������� � �������� ��������';
COMMENT ON COLUMN call_pays.note IS '�������������� ����������';
COMMENT ON SEQUENCE call_pays_id_seq IS '��������� ��������������� ��������';

REVOKE ALL ON call_pays, call_pays_id_seq FROM PUBLIC;
REVOKE ALL ON call_pays, call_pays_id_seq FROM j2eeagent;
REVOKE ALL ON call_pays, call_pays_id_seq FROM apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON call_pays TO apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON call_pays TO j2eeagent;
GRANT SELECT, UPDATE ON call_pays_id_seq TO apacheagent;
GRANT SELECT, UPDATE ON call_pays_id_seq TO j2eeagent;

COMMIT;
