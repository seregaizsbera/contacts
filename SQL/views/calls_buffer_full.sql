DROP VIEW calls_buffer_full;

BEGIN;

CREATE VIEW calls_buffer_full AS
    SELECT
        a.id AS id,
        date(a.moment) AS date,
        time(a.moment) AS time,
        b.name AS direction,
        phone_norm(a.phone) AS phone,
        a.place AS place,
        c.name AS type,
        a.quantity AS quantity,
        a.price AS price,
        a.note AS note
    FROM calls_buffer AS a
	JOIN call_directions AS b ON a.direction = b.id
	JOIN call_types AS c ON a.type = c.id;

COMMENT ON VIEW calls_buffer_full IS '������������ ���������� � ������� (�����)';
COMMENT ON COLUMN calls_buffer_full.id IS '������������� ������';
COMMENT ON COLUMN calls_buffer_full.date IS '���� ������';
COMMENT ON COLUMN calls_buffer_full.time IS '����� ������';
COMMENT ON COLUMN calls_buffer_full.direction IS '����������� ������';
COMMENT ON COLUMN calls_buffer_full.phone IS '����� ��������';
COMMENT ON COLUMN calls_buffer_full.place IS '���������� � �������������� ��������';
COMMENT ON COLUMN calls_buffer_full.type IS '��� ������';
COMMENT ON COLUMN calls_buffer_full.quantity IS '������������ ������';
COMMENT ON COLUMN calls_buffer_full.price IS '��������� ������';
COMMENT ON COLUMN calls_buffer_full.note IS '�������������� ����������';

REVOKE ALL ON calls_buffer_full FROM PUBLIC;
REVOKE ALL ON calls_buffer_full FROM j2eeagent;
REVOKE ALL ON calls_buffer_full FROM apacheagent;
GRANT SELECT ON calls_buffer_full TO apacheagent;
GRANT SELECT ON calls_buffer_full TO j2eeagent;

COMMIT;
