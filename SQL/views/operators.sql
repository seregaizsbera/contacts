DROP VIEW operators;

BEGIN;

CREATE VIEW operators AS
    SELECT
        o.oprname AS name,
        t0.typname AS result,
        (CASE WHEN o.oprkind='l' THEN NULL ELSE t1.typname END) AS left_arg,
        (CASE WHEN o.oprkind='r' THEN NULL ELSE t2.typname END) AS right_arg,
        o.oprkind AS kind,
        o.oprcode AS alias,
        obj_description(p.oid) AS description
    FROM
        pg_operator AS o
        JOIN pg_type AS t0 ON o.oprresult = t0.oid
        JOIN pg_type AS t1 ON t1.oid = (CASE WHEN o.oprkind = 'l' THEN o.oprright ELSE o.oprleft END)
        JOIN pg_type AS t2 ON t2.oid = (CASE WHEN o.oprkind = 'r' THEN o.oprleft ELSE o.oprright END)
        JOIN pg_proc AS p ON RegprocToOid(o.oprcode) = p.oid;

COMMENT ON VIEW operators IS 'Доступные встроенные операторы';
COMMENT ON COLUMN operators.name IS 'Символьное представление оператора';
COMMENT ON COLUMN operators.result IS 'Тип возвращаемого значения';
COMMENT ON COLUMN operators.left_arg IS 'Тип значения левого аргумента';
COMMENT ON COLUMN operators.right_arg IS 'Тип значения правого аргумента';
COMMENT ON COLUMN operators.alias IS 'Название вызываемой процедуры';
COMMENT ON COLUMN operators.description IS 'Описание действия оператора';
COMMENT ON COLUMN operators.kind IS 'Тип оператора';

REVOKE ALL ON operators FROM PUBLIC;
REVOKE ALL ON operators FROM j2eeagent;
REVOKE ALL ON operators FROM apacheagent;
GRANT SELECT ON operators TO apacheagent;
GRANT SELECT ON operators TO j2eeagent;

COMMIT;
