DROP TABLE call_expenses;
DROP SEQUENCE call_expenses_id_seq;

BEGIN;

CREATE SEQUENCE call_expenses_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CYCLE;

CREATE TABLE call_expenses (
    id int4 NOT NULL
            DEFAULT nextval('call_expenses_id_seq'::text),
    report int4 NOT NULL
                REFERENCES call_reports(id)
                ON DELETE RESTRICT
                ON UPDATE RESTRICT,
    kind int4 NOT NULL
              REFERENCES call_expenses_kinds(id)
              ON DELETE RESTRICT
              ON UPDATE RESTRICT,
    expense int4,
    price numeric NOT NULL,
    PRIMARY KEY (id)
);

COMMENT ON TABLE call_expenses IS '������� �� ���������� �������� �� �������� ������';
COMMENT ON COLUMN call_expenses.id IS '������������� �������';
COMMENT ON COLUMN call_expenses.report IS '������������� ������ �� ������';
COMMENT ON COLUMN call_expenses.kind IS '��� �������';
COMMENT ON COLUMN call_expenses.expense IS '������ ��������������� �����';
COMMENT ON COLUMN call_expenses.price IS '��������� ��������������� �����';
COMMENT ON SEQUENCE call_expenses_id_seq IS '��������� ��������������� �������� �� ���������� ��������';

REVOKE ALL ON call_expenses, call_expenses_id_seq FROM PUBLIC;
REVOKE ALL ON call_expenses, call_expenses_id_seq FROM j2eeagent;
REVOKE ALL ON call_expenses, call_expenses_id_seq FROM apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON call_expenses TO j2eeagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON call_expenses TO apacheagent;
GRANT SELECT, UPDATE ON call_expenses_id_seq TO j2eeagent;
GRANT SELECT, UPDATE ON call_expenses_id_seq TO apacheagent;

COMMIT;
