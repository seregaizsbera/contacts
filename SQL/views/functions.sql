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

REVOKE ALL ON functions FROM PUBLIC;
GRANT SELECT ON functions TO apacheagent;
GRANT SELECT ON functions TO j2eeagent;

COMMIT;
