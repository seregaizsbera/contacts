DROP VIEW operators;

BEGIN;

CREATE VIEW operators AS
    SELECT
        am.amname AS acc_name,
        opc.opcname AS ops_name,
        opr.oprname AS ops_comp
    FROM
        pg_am am,
        pg_amop amop,
        pg_opclass opc,
	pg_operator opr
    WHERE (
        (amop.amopid = am.oid) AND
        (amop.amopclaid = opc.oid) AND
        (amop.amopopr = opr.oid)
    );

REVOKE ALL ON operators FROM PUBLIC;
GRANT SELECT ON operators TO apacheagent;
GRANT SELECT ON operators TO j2eeagent;

COMMIT;
