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

COMMENT ON VIEW gprs_full IS 'Объединенная информация об использовании GPRS';
COMMENT ON COLUMN gprs_full.id IS 'Идентификатор случая использования GPRS';
COMMENT ON COLUMN gprs_full.date IS 'День использования GPRS';
COMMENT ON COLUMN gprs_full.time IS 'Время использования GPRS';
COMMENT ON COLUMN gprs_full.direction IS 'Направление трафика';
COMMENT ON COLUMN gprs_full.url IS 'Адрес точки входа в GPRS';
COMMENT ON COLUMN gprs_full.traffic IS 'Количество переданных/полученных байт';
COMMENT ON COLUMN gprs_full.price IS 'Стоимость использования GPRS';
COMMENT ON COLUMN gprs_full.note IS 'Дополнительная информация';

REVOKE ALL ON gprs_full FROM PUBLIC;
REVOKE ALL ON gprs_full FROM j2eeagent;
REVOKE ALL ON gprs_full FROM apacheagent;
GRANT SELECT ON gprs_full TO apacheagent;
GRANT SELECT ON gprs_full TO j2eeagent;

COMMIT;
