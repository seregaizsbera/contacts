DROP VIEW gprs_full;

BEGIN;

CREATE VIEW gprs_full AS
    SELECT
        a.id AS id,
	date(a.moment) AS date,
	time(a.moment) AS time,
	b.name AS direction,
	c.name AS url,
	a.traffic AS traffic,
	a.price AS price,
	a.note AS note
    FROM gprs AS a
    JOIN call_directions AS b ON a.direction = b.id
    JOIN gprs_urls AS c ON a.url = c.id;

COMMENT ON VIEW gprs_full IS '������������ ���������� �� ������������� GPRS';
COMMENT ON COLUMN gprs_full.id IS '������������� ������ ������������� GPRS';
COMMENT ON COLUMN gprs_full.date IS '���� ������������� GPRS';
COMMENT ON COLUMN gprs_full.time IS '����� ������������� GPRS';
COMMENT ON COLUMN gprs_full.direction IS '����������� �������';
COMMENT ON COLUMN gprs_full.url IS '����� ����� ����� � GPRS';
COMMENT ON COLUMN gprs_full.traffic IS '���������� ����������/���������� ����';
COMMENT ON COLUMN gprs_full.price IS '��������� ������������� GPRS';
COMMENT ON COLUMN gprs_full.note IS '�������������� ����������';

REVOKE ALL ON gprs_full FROM PUBLIC;
REVOKE ALL ON gprs_full FROM j2eeagent;
REVOKE ALL ON gprs_full FROM apacheagent;
GRANT SELECT ON gprs_full TO apacheagent;
GRANT SELECT ON gprs_full TO j2eeagent;

COMMIT;
