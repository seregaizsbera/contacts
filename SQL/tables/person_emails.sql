DROP TABLE person_emails;

BEGIN;

CREATE TABLE person_emails (
    person int4 NOT NULL
                REFERENCES persons(id)
                ON DELETE RESTRICT
                ON UPDATE RESTRICT,
    email int4 NOT NULL
               REFERENCES emails(id)
               ON DELETE RESTRICT
               ON UPDATE RESTRICT,
    basic boolean NOT NULL,
    PRIMARY KEY (person, email)
);

CREATE UNIQUE INDEX person_emails_basic_index ON person_emails(unique_basic(person, email, basic));

COMMENT ON TABLE person_emails IS '������� �������������� ������� ����������� ����� �����';
COMMENT ON COLUMN person_emails.person IS '������������� ��������';
COMMENT ON COLUMN person_emails.email IS '������������� ������ ����������� �����';
COMMENT ON COLUMN person_emails.basic IS '������� ��������� ������ ����������� �����';
COMMENT ON INDEX person_emails_basic_index IS '����������� ������������ ��������� ������ ����������� �����';

REVOKE ALL ON person_emails FROM PUBLIC;
REVOKE ALL ON person_emails FROM j2eeagent;
REVOKE ALL ON person_emails FROM apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON person_emails TO apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON person_emails TO j2eeagent;

COMMIT;
