DROP TABLE emails;
DROP SEQUENCE emails_id_seq;

BEGIN;

CREATE SEQUENCE emails_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CYCLE;

CREATE TABLE emails (
    id int4 NOT NULL
            DEFAULT nextval('emails_id_seq'::text),
    email text NOT NULL
               CHECK (email != ''),
    PRIMARY KEY (id)
);

COMMENT ON TABLE emails IS '������ ����������� �����';
COMMENT ON COLUMN emails.id IS '������������� ������ ����������� �����';
COMMENT ON COLUMN emails.email IS '����� ����������� �����';
COMMENT ON SEQUENCE emails_id_seq IS '��������� ��������������� ������� ����������� �����';

REVOKE ALL ON emails, emails_id_seq FROM PUBLIC;
REVOKE ALL ON emails, emails_id_seq FROM j2eeagent;
REVOKE ALL ON emails, emails_id_seq FROM apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON emails TO apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON emails TO j2eeagent;
GRANT SELECT, UPDATE ON emails_id_seq TO apacheagent;
GRANT SELECT, UPDATE ON emails_id_seq TO j2eeagent;

COMMIT;
