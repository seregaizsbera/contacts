DROP VIEW functions;

BEGIN;

CREATE VIEW functions AS
    SELECT
        b.typname AS returns,
        a.proname AS name,
        oidvectortypes(a.proargtypes) AS args,
        a.prosrc AS block
    FROM
        pg_proc AS a
        JOIN pg_type AS b ON a.prorettype = b.oid
    WHERE a.pronargs = 0 or oidvectortypes(a.proargtypes) != '';

COMMENT ON VIEW functions IS '��������� ���������� �������';
COMMENT ON COLUMN functions.returns IS '��� ������������� ��������';
COMMENT ON COLUMN functions.name IS '��� �������';
COMMENT ON COLUMN functions.args IS '����������� �������� ���������';
COMMENT ON COLUMN functions.block IS '�������� ����� ���� �������';

REVOKE ALL ON functions FROM PUBLIC;
REVOKE ALL ON functions FROM j2eeagent;
REVOKE ALL ON functions FROM apacheagent;
GRANT SELECT ON functions TO apacheagent;
GRANT SELECT ON functions TO j2eeagent;

COMMIT;
