DROP VIEW functions;

BEGIN;

CREATE VIEW functions AS
    SELECT
        typname AS returns,
        proname AS name,
        oidvectortypes(proargtypes) AS args,
        prosrc AS block
    FROM pg_proc, pg_type
    WHERE pg_type.oid = prorettype;

COMMENT ON VIEW functions IS '��������� ���������� �������';
COMMENT ON COLUMN functions.returns IS '��� ������������� ��������';
COMMENT ON COLUMN functions.name IS '��� �������';
COMMENT ON COLUMN functions.args IS '����������� �������� ���������';
COMMENT ON COLUMN functions.block IS '�������� ����� ���� �������';

REVOKE ALL ON functions FROM PUBLIC;
GRANT SELECT ON functions TO apacheagent;
GRANT SELECT ON functions TO j2eeagent;

COMMIT;
