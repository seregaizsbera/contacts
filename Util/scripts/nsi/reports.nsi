# ���� � ����������� ��� nsi.perl

# �������� ������ Java, � ������� ��������� ���������
$java_package = "su.sergey.contacts.valueobjects";

# �������� ���������� Java
$java_interface = "Reports";

# ��� ����� �� �������� SQL ��� ����� �������
$sql_file = "reports.sql";

# �������� ������� �� ������������
$sql_table = "reports";

# �������� �������������� ��������
@additional_columns = (
    ["builder", "text", "NOT NULL CHECK (builder <> '')", "��� Java-������ ����������� �������"]
);
    
# ���������� � �����������
$comment = "���� �������";

# [�������� ��������, �������� ���������, ��������� ��������]
@values = (
    ["PERSONS",  1, "����� � ���������", "su.sergey.contacts.person.PersonsReportBuilder"],
    ["SUPPLIES", 2, "����� �� ������������", "su.sergey.contacts.supply.SuppliesReportBuilder"],
    ["CALLS",    3, "����� � �������", "su.sergey.contacts.calls.CallsReportBuilder"]
);
