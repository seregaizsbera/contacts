DROP VIEW operators;

BEGIN;

CREATE VIEW operators AS
    SELECT
        a.oprname AS name,
        b.typname AS left_arg,
	c.typname AS right_arg,
	d.typname AS result,
	a.oprcode AS alias
    FROM
        pg_operator AS a
	JOIN pg_type AS b ON a.oprleft=b.oid
	JOIN pg_type AS c ON a.oprright=c.oid
	JOIN pg_type AS d ON a.oprresult=d.oid;

COMMENT ON VIEW operators IS 'Доступные встроенные бинарные операторы';
COMMENT ON COLUMN operators.name IS 'Символьное представление оператора';
COMMENT ON COLUMN operators.left_arg IS 'Тип значения левого аргумента';
COMMENT ON COLUMN operators.right_arg IS 'Тип значения правого аргумента';
COMMENT ON COLUMN operators.result IS 'Тип возвращаемого значения';
COMMENT ON COLUMN operators.alias IS 'Название вызываемой процедуры';

REVOKE ALL ON operators FROM PUBLIC;
GRANT SELECT ON operators TO apacheagent;
GRANT SELECT ON operators TO j2eeagent;

COMMIT;
