DROP TABLE supply_emails;

BEGIN;

CREATE TABLE supply_emails (
    supply int4 NOT NULL
                REFERENCES supplies(id)
                ON DELETE RESTRICT
                ON UPDATE RESTRICT,
    email int4 NOT NULL
               REFERENCES emails(id)
               ON DELETE RESTRICT
               ON UPDATE RESTRICT,
    PRIMARY KEY (supply, email)
);

COMMENT ON TABLE supply_emails IS '������� �������������� ������� ����������� ����� ������������ � ������������';
COMMENT ON COLUMN supply_emails.supply IS '������������� �����������';
COMMENT ON COLUMN supply_emails.email IS '������������� ������ ����������� �����';

REVOKE ALL ON supply_emails FROM PUBLIC;
REVOKE ALL ON supply_emails FROM j2eeagent;
REVOKE ALL ON supply_emails FROM apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON supply_emails TO apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON supply_emails TO j2eeagent;

COMMIT;
