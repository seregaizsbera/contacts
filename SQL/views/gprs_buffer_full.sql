DROP VIEW gprs_buffer_full;

BEGIN;

CREATE VIEW gprs_buffer_full AS
    SELECT
        a.id AS id,
	date(a.moment) AS date,
	time(a.moment) AS time,
	b.name AS direction,
	c.name AS url,
	a.traffic AS traffic,
	a.price AS price,
	a.note AS note
    FROM gprs_buffer AS a
    JOIN call_directions AS b ON a.direction = b.id
    JOIN gprs_urls AS c ON a.url = c.id;

COMMENT ON VIEW gprs_buffer_full IS '������������ ���������� �� ������������� GPRS (�����)';
COMMENT ON COLUMN gprs_buffer_full.id IS '������������� ������ ������������� GPRS';
COMMENT ON COLUMN gprs_buffer_full.date IS '���� ������������� GPRS';
COMMENT ON COLUMN gprs_buffer_full.time IS '����� ������������� GPRS';
COMMENT ON COLUMN gprs_buffer_full.direction IS '����������� �������';
COMMENT ON COLUMN gprs_buffer_full.url IS '����� ����� ����� � GPRS';
COMMENT ON COLUMN gprs_buffer_full.traffic IS '���������� ����������/���������� ����';
COMMENT ON COLUMN gprs_buffer_full.price IS '��������� ������������� GPRS';
COMMENT ON COLUMN gprs_buffer_full.note IS '�������������� ����������';

REVOKE ALL ON gprs_buffer_full FROM PUBLIC;
REVOKE ALL ON gprs_buffer_full FROM j2eeagent;
REVOKE ALL ON gprs_buffer_full FROM apacheagent;
GRANT SELECT ON gprs_buffer_full TO apacheagent;
GRANT SELECT ON gprs_buffer_full TO j2eeagent;

COMMIT;
