INSERT INTO properties (name, value, format, type, maker, note) VALUES
    ('last_birthdays_check', '17.07.1980', 'dd.MM.yyyy', 'java.util.Date', 'su.sergey.contacts.properties.impl.DatePropertyMaker', '���� ��������� �������� ���������� � ��������� ���� ��������');
INSERT INTO properties (name, value, format, type, maker, note) VALUES
    ('report_folder', '/tmp', '^/(\w+/)+$', 'java.io.File', null, '������� ��� ���������� �������');
INSERT INTO properties (name, value, format, type, maker, note) VALUES
    ('query_history_size', '100', '^\d+$', 'java.lang.Integer', null, '������ ������������ ������� ��������');
    