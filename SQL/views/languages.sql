DROP VIEW languages;

BEGIN;

CREATE VIEW languages AS
    SELECT
        pg_language.lanname,
        pg_language.lanispl,
        pg_language.lanpltrusted,
        pg_language.lanplcallfoid,
        pg_language.lancompiler
    FROM pg_language;

REVOKE ALL ON languages FROM PUBLIC;
GRANT SELECT ON languages TO apacheagent;
GRANT SELECT ON languages TO j2eeagent;

COMMIT;
    