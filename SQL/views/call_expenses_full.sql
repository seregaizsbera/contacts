DROP VIEW call_expenses_full;

BEGIN;

CREATE VIEW call_expenses_full AS
    SELECT 
        a.id AS id,
	b.name AS kind,
	a.expense AS expense,
	b.units AS units,
	a.price AS price,
	(CASE
	     WHEN expense IS NOT NULL and expense <> 0 THEN price / expense
	     ELSE null
	 END)::numeric(30, 6) AS price_per_unit,
	a.report AS report,
	c.first_day AS first_day,
	c.last_day AS last_day
	FROM call_expenses AS a
	    JOIN call_expenses_kinds AS b ON a.kind = b.id
	    JOIN call_reports AS c ON a.report = c.id;

COMMENT ON VIEW call_expenses_full IS 'Объединенная информация о расходах по мобильному телефону за отчетный период';
COMMENT ON COLUMN call_expenses_full.id IS 'Идентификатор расхода';
COMMENT ON COLUMN call_expenses_full.kind IS 'Вид расхода';
COMMENT ON COLUMN call_expenses_full.expense IS 'Размер предоставленных услуг';
COMMENT ON COLUMN call_expenses_full.units IS 'Название еденицы измерения';
COMMENT ON COLUMN call_expenses_full.price IS 'Стоимость предоставленных услуг';
COMMENT ON COLUMN call_expenses_full.price_per_unit IS 'Цена услуги за единицу измерения';
COMMENT ON COLUMN call_expenses_full.report IS 'Идентификатор отчета за период';
COMMENT ON COLUMN call_expenses_full.first_day IS 'Начало периода';
COMMENT ON COLUMN call_expenses_full.last_day IS 'Конец периода';

REVOKE ALL ON call_expenses_full FROM PUBLIC;
REVOKE ALL ON call_expenses_full FROM j2eeagent;
REVOKE ALL ON call_expenses_full FROM apacheagent;
GRANT SELECT ON call_expenses_full TO apacheagent;
GRANT SELECT ON call_expenses_full TO j2eeagent;

COMMIT;
