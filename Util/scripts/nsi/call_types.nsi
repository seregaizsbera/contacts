# ���� � ����������� ��� nsi.perl

# �������� ������ Java, � ������� ��������� ���������
$java_package = "su.sergey.contacts.valueobjects";

# �������� ���������� Java
$java_interface = "CallTypes";

# ��� ����� �� �������� SQL ��� ����� �������
$sql_file = "call_types.sql";

# �������� ������� �� ������������
$sql_table = "call_types";

# �������� �������������� ��������
@additional_columns = ();
    
# ���������� � �����������
$comment = "���� �������";

# [�������� ��������, �������� ���������, ��������� ��������]
@values = (
    ["PHONE", 1, "�������"],
    ["SMS", 2, "SMS"],
    ["SOS", 3, "���"]
);
