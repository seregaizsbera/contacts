DROP TABLE gprs;

BEGIN;

CREATE TABLE gprs (
    id int4 NOT NULL
            DEFAULT nextval('calls_id_seq'::text),
    moment timestamp NOT NULL,
    direction int4 NOT NULL
                   REFERENCES call_directions(id)
		   ON DELETE RESTRICT
		   ON UPDATE RESTRICT,
    url int4 NOT NULL
             REFERENCES gprs_urls(id)
	     ON DELETE RESTRICT
	     ON UPDATE RESTRICT,
    traffic int4 NOT NULL,
    price numeric NOT NULL,
    note text CHECK (note != ''),             
    PRIMARY KEY (id)
);

COMMENT ON TABLE gprs IS '���������� �� ������������� GPRS';
COMMENT ON COLUMN gprs.id IS '������������� ������ ������������� GPRS';
COMMENT ON COLUMN gprs.moment IS '����� ������������� GPRS';
COMMENT ON COLUMN gprs.direction IS '����������� �������';
COMMENT ON COLUMN gprs.url IS '������������� ������ ����� ����� � GPRS';
COMMENT ON COLUMN gprs.traffic IS '���������� ����������/���������� ����';
COMMENT ON COLUMN gprs.id IS '��������� ������������� GPRS';
COMMENT ON COLUMN gprs.note IS '����������';

REVOKE ALL ON gprs FROM PUBLIC;
REVOKE ALL ON gprs FROM j2eeagent;
REVOKE ALL ON gprs FROM apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON gprs TO apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON gprs TO j2eeagent;

COMMIT;
