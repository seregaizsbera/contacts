# ���� � ����������� ��� nsi.perl

# �������� ������ Java, � ������� ��������� ���������
$java_package = "su.sergey.contacts.inquiry";

# �������� ���������� Java
$java_interface = "InquiryModes";

# ��������� �� ������������ ��������� �������� �������� ������ ��������
$need_atomic_values = 1;

# ��� ����� �� �������� SQL
$sql_file = "inquiry_modes.sql";

# �������� ������� �� ������������
$sql_table = "inquiry_modes";

# ���������� � �����������
$comment = "������ ��������� ���������� ������ �������";

# [�������� ��������, �������� ���������, ��������� ��������]
@values = (
    ["COLLECTION", 1, "���������"],
    ["MAP",        2, "�����������"]
);
