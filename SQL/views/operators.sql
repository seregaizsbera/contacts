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

COMMENT ON VIEW operators IS '��������� ���������� �������� ���������';
COMMENT ON COLUMN operators.name IS '���������� ������������� ���������';
COMMENT ON COLUMN operators.left_arg IS '��� �������� ������ ���������';
COMMENT ON COLUMN operators.right_arg IS '��� �������� ������� ���������';
COMMENT ON COLUMN operators.result IS '��� ������������� ��������';
COMMENT ON COLUMN operators.alias IS '�������� ���������� ���������';

REVOKE ALL ON operators FROM PUBLIC;
GRANT SELECT ON operators TO apacheagent;
GRANT SELECT ON operators TO j2eeagent;

COMMIT;
