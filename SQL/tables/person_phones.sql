DROP TABLE person_phones;

BEGIN;

CREATE TABLE person_phones (
    person int4 NOT NULL
                REFERENCES persons(id)
                ON DELETE RESTRICT
                ON UPDATE RESTRICT,
    phone int4 NOT NULL
               REFERENCES phones(id)
               ON DELETE RESTRICT
               ON UPDATE RESTRICT,
    PRIMARY KEY (person, phone)
);

COMMENT ON TABLE person_phones IS '������� �������������� ��������� �����';
COMMENT ON COLUMN person_phones.person IS '������������� ��������';
COMMENT ON COLUMN person_phones.phone IS '������������� ��������';

REVOKE ALL ON person_phones FROM PUBLIC;
GRANT SELECT, INSERT, UPDATE, DELETE ON person_phones TO apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON person_phones TO j2eeagent;

COMMIT;
