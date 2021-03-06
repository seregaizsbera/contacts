DROP VIEW shnippers;

BEGIN;

CREATE VIEW shnippers AS
    SELECT
        a.id AS id,
        a.last || ' ' || a.first || coalesce(' ' || a.middle, '') AS name
    FROM
        persons AS a
	JOIN shnip AS b ON a.id=b.person
	WHERE b.tutor;

COMMENT ON VIEW shnippers IS '������������� �����';
COMMENT ON COLUMN shnippers.id IS '������������� ��������';
COMMENT ON COLUMN shnippers.name IS '�������, ��� � �������� ��������';

REVOKE ALL ON shnippers FROM PUBLIC;
REVOKE ALL ON shnippers FROM j2eeagent;
REVOKE ALL ON shnippers FROM apacheagent;
GRANT SELECT ON shnippers TO apacheagent;
GRANT SELECT ON shnippers TO j2eeagent;

COMMIT;
