DROP VIEW languages;

BEGIN;

CREATE VIEW languages AS
    SELECT
        pg_language.lanname AS name,
        pg_language.lanispl AS added_by_user,
        pg_language.lanpltrusted AS trusted,
        pg_language.lanplcallfoid AS handler,
        pg_language.lancompiler AS compiler
    FROM pg_language;

COMMENT ON VIEW languages IS '��������� ���������� ����������� ����� ����������������';
COMMENT ON COLUMN languages.name IS '�������� �����';
COMMENT ON COLUMN languages.added_by_user IS '������� �����, ������������ �������������';
COMMENT ON COLUMN languages.trusted IS '������� ����������� �����������';
COMMENT ON COLUMN languages.handler IS '������������� ���������-�����������';
COMMENT ON COLUMN languages.compiler IS '�������� �����������';

REVOKE ALL ON languages FROM PUBLIC;
GRANT SELECT ON languages TO apacheagent;
GRANT SELECT ON languages TO j2eeagent;

COMMIT;
    