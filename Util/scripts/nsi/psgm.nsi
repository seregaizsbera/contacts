# ���� � ����������� ��� nsi.perl

# �������� ������ Java, � ������� ��������� ���������
$java_package = "su.sergey.contacts.valueobjects";

# �������� ���������� Java
$java_interface = "PersonSearchGroupModes";

# ��� ����� �� �������� SQL
$sql_file = "psgm.sql";

# �������� ������� �� ������������
$sql_table = "psgm";

# ���������� � �����������
$comment = "������ ������ �� ������� �����";

# [�������� ��������, �������� ���������, ��������� ��������]
@values = (
    ["ANY",      0, "���"],
    ["COWORKER", 1, "����������"],
    ["MSU",      2, "���"],
    ["SHNIP",    3, "����"],
    ["RELATED",  4, "������������"],
    ["FRIEND",   5, "������"]
);
