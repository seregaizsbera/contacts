# Файл с настройками для nsi.perl

# Название пакета Java, в котором определен интерфейс
$java_package = "su.sergey.contacts.valueobjects";

# Название интерфейса Java
$java_interface = "CallTypes";

# Имя файла со скриптом SQL для схемы таблицы
$sql_file = "call_types.sql";

# Название таблицы со справочником
$sql_table = "call_types";

# Описание дополнительных столбцов
@additional_columns = ();
    
# Коментарий к справочнику
$comment = "Типы звонков";

# [Числовое значение, название константы, смысловое значение]
@values = (
    ["PHONE", 1, "Телефон"],
    ["SMS", 2, "SMS"],
    ["SOS", 3, "МЧС"]
);
