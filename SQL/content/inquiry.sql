INSERT INTO inquiry (alias, query, scope, mode, role, description)
    VALUES ('expense_kinds', 'SELECT id, name FROM call_expenses_kinds ORDER BY name', null, 1, 'Sergey', '���� �������� �� ��������� �����');

INSERT INTO inquiry (alias, query, scope, mode, role, description)
    VALUES ('inquire_shnippers_2', 'SELECT id, name FROM shnippers ORDER BY name', null, 1, 'Sergey', '������������� ����');
    
INSERT INTO inquiry (alias, query, scope, mode, role, description)
    VALUES ('supplyKinds_2', 'SELECT id, name FROM supply_kinds ORDER BY CASE WHEN id=100 THEN ''_'' ELSE name END', null, 1, 'AllAuthenticated', '���� ����������� �� ���� ������������ (���������)');
    
INSERT INTO inquiry (alias, query, scope, mode, role, description)
    VALUES ('supplyKinds_4', 'SELECT id, name FROM supply_kinds', null, 2, 'AllAuthenticated', '���� ����������� �� ���� ������������ (�����������)');
    
INSERT INTO inquiry (alias, query, scope, mode, role, description)
    VALUES ('inquire_call_types_1', 'SELECT id, name FROM call_types ORDER BY id', 4, 1, null, '���� ���������� �������');
    
INSERT INTO inquiry (alias, query, scope, mode, role, description)
    VALUES ('inquire_phone_types_1', 'SELECT id, name FROM phone_types ORDER BY CASE WHEN id=100 THEN 0 ELSE id END', 4, 1, null, '���� ��������� (���������)');
    
INSERT INTO inquiry (alias, query, scope, mode, role, description)
    VALUES ('inquire_call_directions_1', 'SELECT id, name FROM call_directions ORDER BY id', 4, 1, null, '����������� ���������� �������');

INSERT INTO inquiry (alias, query, scope, mode, role, description)
    VALUES ('inquire_msu_departments_2', 'SELECT id, name FROM msu_departments ORDER BY name', 4, 1, null, '���������� ���');

INSERT INTO inquiry (alias, query, scope, mode, role, description)
    VALUES ('inquire_genders_4', 'SELECT id, name FROM genders', 4, 2, null, '���������� ������������� ����� (�����������)');

INSERT INTO inquiry (alias, query, scope, mode, role, description)
    VALUES ('inquire_genders_1', 'SELECT id, name FROM genders ORDER BY id', 4, 1, null, '���������� ������������� ����� (���������)');

INSERT INTO inquiry (alias, query, scope, mode, role, description)
    VALUES ('inquire_months_4', 'SELECT id, name FROM months', 4, 2, null, '������ (�����������)');

INSERT INTO inquiry (alias, query, scope, mode, role, description)
    VALUES ('inquire_months_1', 'SELECT id, name FROM months ORDER BY id', 4, 1, null, '������ (���������)');

INSERT INTO inquiry (alias, query, scope, mode, role, description)
    VALUES ('inquire_supply_property_forms_2', 'SELECT id, name FROM supply_property_forms ORDER BY name', 4, 1, null, '���� ������������� �����������');

INSERT INTO inquiry (alias, query, scope, mode, role, description)
    VALUES ('inquire_psgm_1', 'SELECT id, name FROM psgm ORDER BY id', 4, 1, null, '������ ������ ����� �� �������');

INSERT INTO inquiry (alias, query, scope, mode, role, description)
    VALUES ('inquire_phone_types_4', 'SELECT id, name FROM phone_types', 4, 2, null, '���� ��������� (�����������)');
