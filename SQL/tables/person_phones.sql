DROP TABLE person_phones;
DROP FUNCTION unique_basic(int4, int4, boolean);

BEGIN;

CREATE FUNCTION unique_basic(int4, int4, boolean)
    RETURNS text
    AS 'return $_[2] eq "t" ? "$_[0]G" : "$_[0]H$_[1]"'
    LANGUAGE 'perl';

CREATE TABLE person_phones (
    person int4 NOT NULL
                REFERENCES persons(id)
                ON DELETE RESTRICT
                ON UPDATE RESTRICT,
    phone int4 NOT NULL
               REFERENCES phones(id)
               ON DELETE RESTRICT
               ON UPDATE RESTRICT,
    basic boolean NOT NULL,
    PRIMARY KEY (person, phone)
);

CREATE UNIQUE INDEX person_phones_basic_index ON person_phones(unique_basic(person, phone, basic));

COMMENT ON TABLE person_phones IS '������� �������������� ��������� �����';
COMMENT ON COLUMN person_phones.person IS '������������� ��������';
COMMENT ON COLUMN person_phones.phone IS '������������� ��������';
COMMENT ON COLUMN person_phones.basic IS '������� ��������� ��������';
COMMENT ON INDEX person_phones_basic_index IS '����������� ������������ ��������� ��������';
COMMENT ON FUNCTION unique_basic(int4, int4, boolean) IS '���������� ��������, ���������� ��� ���� �������� ��������� ������ ��������';

REVOKE ALL ON person_phones FROM PUBLIC;
REVOKE ALL ON person_phones FROM j2eeagent;
REVOKE ALL ON person_phones FROM apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON person_phones TO apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON person_phones TO j2eeagent;

COMMIT;
