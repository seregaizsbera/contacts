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

COMMENT ON VIEW languages IS 'Доступные встроенные процедурные языки программирования';
COMMENT ON COLUMN languages.name IS 'Название языка';
COMMENT ON COLUMN languages.added_by_user IS 'Признак языка, добавленного пользователем';
COMMENT ON COLUMN languages.trusted IS 'Признак безопасного обработчика';
COMMENT ON COLUMN languages.handler IS 'Идентификатор процедуры-обработчика';
COMMENT ON COLUMN languages.compiler IS 'Название компилятора';

REVOKE ALL ON languages FROM PUBLIC;
GRANT SELECT ON languages TO apacheagent;
GRANT SELECT ON languages TO j2eeagent;

COMMIT;
    