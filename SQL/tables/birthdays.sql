DROP TABLE birthdays;

BEGIN;

CREATE TABLE birthdays (
    person int4 NOT NULL
                REFERENCES persons(id)
	        ON DELETE RESTRICT
	        ON UPDATE RESTRICT,
    birthday date NOT NULL,
    birthyear date,
    PRIMARY KEY (person)
);

COMMENT ON TABLE birthdays IS 'Дни рождения';
COMMENT ON COLUMN birthdays.person IS 'Идентификатор человека';
COMMENT ON COLUMN birthdays.birthday IS 'День рождения';
COMMENT ON COLUMN birthdays.birthday IS 'Год рождения';

REVOKE ALL ON birthdays FROM PUBLIC;
REVOKE ALL ON birthdays FROM j2eeagent;
REVOKE ALL ON birthdays FROM apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON birthdays TO apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON birthdays TO j2eeagent;

COMMIT;
