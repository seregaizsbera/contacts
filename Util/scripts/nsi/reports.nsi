# Файл с настройками для nsi.perl

# Название пакета Java, в котором определен интерфейс
$java_package = "su.sergey.contacts.valueobjects";

# Название интерфейса Java
$java_interface = "Reports";

# Имя файла со скриптом SQL для схемы таблицы
$sql_file = "reports.sql";

# Название таблицы со справочником
$sql_table = "reports";

# Описание дополнительных столбцов
@additional_columns = (
    ["builder", "text", "NOT NULL CHECK (builder <> '')", "Имя Java-класса построителя отчетов"]
);
    
# Коментарий к справочнику
$comment = "Виды отчетов";

# [Числовое значение, название константы, смысловое значение]
@values = (
    ["PERSONS",  1, "Отчет о личностях", "su.sergey.contacts.person.PersonsReportBuilder"],
    ["SUPPLIES", 2, "Отчет об организациях", "su.sergey.contacts.supply.SuppliesReportBuilder"],
    ["CALLS",    3, "Отчет о звонках", "su.sergey.contacts.calls.CallsReportBuilder"]
);
