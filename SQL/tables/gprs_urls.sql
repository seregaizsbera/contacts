DROP TABLE gprs_urls;
DROP SEQUENCE gprs_urls_id_seq;

BEGIN;

CREATE SEQUENCE gprs_urls_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CYCLE;

CREATE TABLE gprs_urls (
    id int4 NOT NULL
            DEFAULT nextval('gprs_urls_id_seq'::text),
    name text NOT NULL
             CHECK (name != '')
             UNIQUE,
    PRIMARY KEY (id)
);

COMMENT ON TABLE gprs_urls IS '������ ����� ����� � GPRS';
COMMENT ON COLUMN gprs_urls.id IS '������������� ������ ����� ����� � GPRS';
COMMENT ON COLUMN gprs_urls.name IS '����� ����� ����� � GPRS';
COMMENT ON SEQUENCE gprs_urls_id_seq IS '��������� ��������������� ������� ����� ����� � GPRS';

REVOKE ALL ON gprs_urls, gprs_urls_id_seq FROM PUBLIC;
REVOKE ALL ON gprs_urls, gprs_urls_id_seq FROM j2eeagent;
REVOKE ALL ON gprs_urls, gprs_urls_id_seq FROM apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON gprs_urls TO apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON gprs_urls TO j2eeagent;
GRANT SELECT, UPDATE ON gprs_urls_id_seq TO apacheagent;
GRANT SELECT, UPDATE ON gprs_urls_id_seq TO j2eeagent;

COMMIT;
