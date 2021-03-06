DROP INDEX calls_moment_index;
DROP TABLE calls;
DROP SEQUENCE calls_id_seq;

BEGIN;

CREATE SEQUENCE calls_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CYCLE;

CREATE TABLE calls (
    id int4 NOT NULL
            DEFAULT nextval('calls_id_seq'::text),
    moment timestamp NOT NULL,
    direction int4 NOT NULL
                   REFERENCES call_directions(id)
		           ON DELETE RESTRICT
		           ON UPDATE RESTRICT,
    phone text CHECK (phone != ''),
    place text CHECK (place != ''),
    type int4 NOT NULL
              REFERENCES call_types(id)
	          ON DELETE RESTRICT
	          ON UPDATE RESTRICT,
    quantity time NOT NULL,
    price numeric NOT NULL,
    note text CHECK (note != ''),
    PRIMARY KEY (id)
);

CREATE INDEX calls_moment_index ON calls(moment);

COMMENT ON TABLE calls IS '���������� � �������';
COMMENT ON COLUMN calls.id IS '������������� ������';
COMMENT ON COLUMN calls.moment IS '����� ������';
COMMENT ON COLUMN calls.direction IS '����������� ������';
COMMENT ON COLUMN calls.phone IS '����� ��������';
COMMENT ON COLUMN calls.place IS '���������� � �������������� ��������';
COMMENT ON COLUMN calls.type IS '��� ������';
COMMENT ON COLUMN calls.quantity IS '������������ ������';
COMMENT ON COLUMN calls.price IS '��������� ������';
COMMENT ON COLUMN calls.note IS '�������������� ����������';
COMMENT ON SEQUENCE calls_id_seq IS '��������� ��������������� �������';
COMMENT ON INDEX calls_moment_index IS '����������� ������ �� ������� ������';

REVOKE ALL ON calls, calls_id_seq FROM PUBLIC;
REVOKE ALL ON calls, calls_id_seq FROM j2eeagent;
REVOKE ALL ON calls, calls_id_seq FROM apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON calls TO apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON calls TO j2eeagent;
GRANT SELECT, UPDATE ON calls_id_seq TO apacheagent;
GRANT SELECT, UPDATE ON calls_id_seq TO j2eeagent;

COMMIT;
