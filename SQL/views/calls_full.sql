DROP VIEW calls_full;

BEGIN;

CREATE VIEW calls_full AS
    SELECT
        a.id AS id,
        date(a.moment) AS date,
        time(a.moment) AS time,
        b.name AS direction,
        phone_norm(a.phone) AS phone,
        a.place AS place,
        c.name AS type,
        a.quantity AS quantity,
        a.price AS price,
        a.note AS note
    FROM calls AS a
	JOIN call_directions AS b ON a.direction = b.id
	JOIN call_types AS c ON a.type = c.id;

COMMENT ON VIEW calls_full IS 'Объединенная информация о звонках';
COMMENT ON COLUMN calls_full.id IS 'Идентификатор звонка';
COMMENT ON COLUMN calls_full.date IS 'День звонка';
COMMENT ON COLUMN calls_full.time IS 'Время звонка';
COMMENT ON COLUMN calls_full.direction IS 'Направление звонка';
COMMENT ON COLUMN calls_full.phone IS 'Номер абонента';
COMMENT ON COLUMN calls_full.place IS 'Информация о местоположении абонента';
COMMENT ON COLUMN calls_full.type IS 'Тип звонка';
COMMENT ON COLUMN calls_full.quantity IS 'Длительность звонка';
COMMENT ON COLUMN calls_full.price IS 'Стоимость звонка';
COMMENT ON COLUMN calls_full.note IS 'Дополнительная информация';

REVOKE ALL ON calls_full FROM PUBLIC;
REVOKE ALL ON calls_full FROM j2eeagent;
REVOKE ALL ON calls_full FROM apacheagent;
GRANT SELECT ON calls_full TO apacheagent;
GRANT SELECT ON calls_full TO j2eeagent;

COMMIT;
