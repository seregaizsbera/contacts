# ���� � ����������� ��� nsi.perl

# �������� ������ Java, � ������� ��������� ���������
$java_package = "su.sergey.contacts.valueobjects";

# �������� ���������� Java
$java_interface = "CallExpensesKinds";

# ��� ����� �� �������� SQL ��� ����� �������
$sql_file = "call_expenses_kinds.sql";

# �������� ������� �� ������������
$sql_table = "call_expenses_kinds";

# �������� �������������� ��������
@additional_columns = (
    ["units", "text", "CHECK (units <> '')", "�������� ������� ���������"]
);
    
# ���������� � �����������
$comment = "���� �������� �� ���������� ��������";

# [�������� ��������, �������� ���������, ��������� ��������]
@values = (
    ["AA",  1, "����������� �����",                                 undef],
    ["AB",  2, "���������������� ����",                             undef],
    ["AC",  3, "������������ ������",                               undef],
    ["AD",  4, "���",                                               undef],
    ["AE",  5, "����� � ������",                                    undef],
    ["AF",  6, "SMS ���������: �� ���� �. ������",                   "��"],
    ["AG",  7, "SMS ��������",                                       "��"],
    ["AH",  8, "SMS ���������",                                      "��"],
    ["AI",  9, "�������� ����� (���)",                              "���"],
    ["AJ", 10, "�������� ����� (���)",                              "���"],
    ["AK", 11, "�������� �����: �� ���� �. ������ (���)",           "���"],
    ["AL", 12, "�������� �����: �� ���� �. ������ (���)",           "���"],
    ["AM", 13, "�������� �����: ������� ��������� �. ������ (���)", "���"],
    ["AN", 14, "�������� �����: ������� ��������� �. ������ (���)", "���"],
    ["AO", 15, "��������� �����: �� ���� �. ������ (���)",          "���"],
    ["AP", 16, "��������� �����: �� ���� �. ������ (���)",          "���"],
    ["AQ", 17, "��������� �����: ��������� (���)",                  "���"],
    ["AR", 18, "��������� �����: ��������� (���)",                  "���"],
    ["AS", 19, "��������� �����: ��������� ������� ����� (���)",    "���"],
    ["AT", 20, "��������� �����: ��������� ������� ����� (���)",    "���"],
    ["AU", 21, "��������� �����: ��������� (���)",                  "���"],
    ["AV", 22, "��������� �����: ��������� (���)",                  "���"],
    ["AW", 23, "��������� �����: �������",                          "���"],
    ["AX", 24, "��������� �����: ������ ��������",                  "���"],
    ["AY", 25, "�������� ������ GPRS-��������",              "40960 ����"],
    ["AZ", 26, "��������� ������ GPRS-��������",             "40960 ����"],
    ["BA", 27, "�������. �������� �����. ������: ������ GSM",       "���"],
    ["BB", 28, "�������. ��������� �����. ������: ������ GSM",      "���"],
    ["BC", 29, "�������. �������� �����. �-� ���: ���������",       "���"],
    ["BD", 30, "�������. �c������� �����. �-� ���: ���������",      "���"],
    ["BE", 31, "�c������� �����. �������������",                    "���"]
);
