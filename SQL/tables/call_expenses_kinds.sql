DROP TABLE call_expenses_kinds;

BEGIN;

CREATE TABLE call_expenses_kinds (
    id int4 NOT NULL PRIMARY KEY,
    name text NOT NULL CHECK (name <> ''),
    units text CHECK (units <> '')
);

COMMENT ON TABLE call_expenses_kinds IS '���� �������� �� ���������� ��������';

COMMENT ON COLUMN call_expenses_kinds.id IS '�������� ��������';
COMMENT ON COLUMN call_expenses_kinds.name IS '��������� ��������';
COMMENT ON COLUMN call_expenses_kinds.units IS '�������� ������� ���������';

REVOKE ALL ON call_expenses_kinds FROM PUBLIC;
REVOKE ALL ON call_expenses_kinds FROM j2eeagent;
REVOKE ALL ON call_expenses_kinds FROM apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON call_expenses_kinds TO j2eeagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON call_expenses_kinds TO apacheagent;

INSERT INTO call_expenses_kinds (id, name, units) VALUES (1, '����������� �����', null);
INSERT INTO call_expenses_kinds (id, name, units) VALUES (2, '���������������� ����', null);
INSERT INTO call_expenses_kinds (id, name, units) VALUES (3, '������������ ������', null);
INSERT INTO call_expenses_kinds (id, name, units) VALUES (4, '���', null);
INSERT INTO call_expenses_kinds (id, name, units) VALUES (5, '����� � ������', null);
INSERT INTO call_expenses_kinds (id, name, units) VALUES (6, 'SMS ���������: �� ���� �. ������', '��');
INSERT INTO call_expenses_kinds (id, name, units) VALUES (7, 'SMS ��������', '��');
INSERT INTO call_expenses_kinds (id, name, units) VALUES (8, 'SMS ���������', '��');
INSERT INTO call_expenses_kinds (id, name, units) VALUES (9, '�������� �����', '���');
INSERT INTO call_expenses_kinds (id, name, units) VALUES (10, '�������� �����', '���');
INSERT INTO call_expenses_kinds (id, name, units) VALUES (11, '�������� �����: ���������', '���');
INSERT INTO call_expenses_kinds (id, name, units) VALUES (12, '�������� �����: ���������', '���');
INSERT INTO call_expenses_kinds (id, name, units) VALUES (13, '�������� �����: �� ���� �. ������', '���');
INSERT INTO call_expenses_kinds (id, name, units) VALUES (14, '�������� �����: �� ���� �. ������', '���');
INSERT INTO call_expenses_kinds (id, name, units) VALUES (15, '�������� �����: ������� ��������� �. ������', '���');
INSERT INTO call_expenses_kinds (id, name, units) VALUES (16, '�������� �����: ������� ��������� �. ������', '���');
INSERT INTO call_expenses_kinds (id, name, units) VALUES (17, '��������� �����: �� ���� �. ������', '���');
INSERT INTO call_expenses_kinds (id, name, units) VALUES (18, '��������� �����: �� ���� �. ������', '���');
INSERT INTO call_expenses_kinds (id, name, units) VALUES (19, '��������� �����: ���������', '���');
INSERT INTO call_expenses_kinds (id, name, units) VALUES (20, '��������� �����: ���������', '���');
INSERT INTO call_expenses_kinds (id, name, units) VALUES (21, '��������� �����: ��������� ������� �����', '���');
INSERT INTO call_expenses_kinds (id, name, units) VALUES (22, '��������� �����: ��������� ������� �����', '���');
INSERT INTO call_expenses_kinds (id, name, units) VALUES (23, '��������� �����: ���������', '���');
INSERT INTO call_expenses_kinds (id, name, units) VALUES (24, '��������� �����: ���������', '���');
INSERT INTO call_expenses_kinds (id, name, units) VALUES (25, '��������� �����: �������', '���');
INSERT INTO call_expenses_kinds (id, name, units) VALUES (26, '��������� �����: ������ ��������', '���');
INSERT INTO call_expenses_kinds (id, name, units) VALUES (27, '�������� ������ GPRS-��������', '40960 ����');
INSERT INTO call_expenses_kinds (id, name, units) VALUES (28, '��������� ������ GPRS-��������', '40960 ����');
INSERT INTO call_expenses_kinds (id, name, units) VALUES (29, '�������. �������� �����. ������: ������ GSM', '���');
INSERT INTO call_expenses_kinds (id, name, units) VALUES (30, '�������. ��������� �����. ������: ������ GSM', '���');
INSERT INTO call_expenses_kinds (id, name, units) VALUES (31, '�������. �������� �����. �-� ���: ���������', '���');
INSERT INTO call_expenses_kinds (id, name, units) VALUES (32, '�������. �c������� �����. �-� ���: ���������', '���');
INSERT INTO call_expenses_kinds (id, name, units) VALUES (33, '�c������� �����. �������������', '���');
INSERT INTO call_expenses_kinds (id, name, units) VALUES (34, '���������� ������������ ������', null);
INSERT INTO call_expenses_kinds (id, name, units) VALUES (35, 'SMS ��������: ��������', '��');
INSERT INTO call_expenses_kinds (id, name, units) VALUES (36, '����������� ���������� �� ������', null);
INSERT INTO call_expenses_kinds (id, name, units) VALUES (37, '���������� ����������� �����', null);
INSERT INTO call_expenses_kinds (id, name, units) VALUES (38, '��������� ��������� �����', null);
INSERT INTO call_expenses_kinds (id, name, units) VALUES (39, '�������� �����: �������', '���');
INSERT INTO call_expenses_kinds (id, name, units) VALUES (40, 'SMS ���������', '��');
INSERT INTO call_expenses_kinds (id, name, units) VALUES (41, '�������� ������ GPRS-WAP', '����');
INSERT INTO call_expenses_kinds (id, name, units) VALUES (42, '�c������� ������ GPRS-WAP', '����');
INSERT INTO call_expenses_kinds (id, name, units) VALUES (43, '�������� ������ GPRS-��������', '����');
INSERT INTO call_expenses_kinds (id, name, units) VALUES (44, '��������� ������ GPRS-��������', '����');
INSERT INTO call_expenses_kinds (id, name, units) VALUES (45, '���-������: ��������� 20', '��');

COMMIT;
