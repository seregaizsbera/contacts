DROP TABLE birthdays;

BEGIN;

CREATE TABLE birthdays (
    person int4 NOT NULL
                REFERENCES persons(id)
	        ON DELETE RESTRICT
	        ON UPDATE RESTRICT,
    birthday date NOT NULL
                  CHECK (date_le(birthday, date(now()))),
    PRIMARY KEY (person)
);

COMMENT ON TABLE birthdays IS '��� ��������';
COMMENT ON COLUMN birthdays.person IS '������������� ��������';
COMMENT ON COLUMN birthdays.birthday IS '���� ��������';

REVOKE ALL ON birthdays FROM PUBLIC;
GRANT SELECT, INSERT, UPDATE, DELETE ON birthdays TO apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON birthdays TO j2eeagent;

COMMIT;
