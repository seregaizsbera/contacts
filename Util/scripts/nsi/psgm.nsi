# Файл с настройками для nsi.perl

# Название пакета Java, в котором определен интерфейс
$java_package = "su.sergey.contacts.valueobjects";

# Название интерфейса Java
$java_interface = "PersonSearchGroupModes";

# Имя файла со скриптом SQL
$sql_file = "psgm.sql";

# Название таблицы со справочником
$sql_table = "psgm";

# Коментарий к справочнику
$comment = "Режимы поиска по группам людей";

# [Числовое значение, название константы, смысловое значение]
@values = (
    ["ANY",      0, "Все"],
    ["COWORKER", 1, "Сослуживцы"],
    ["MSU",      2, "МГУ"],
    ["SHNIP",    3, "ШНИП"],
    ["RELATED",  4, "Родственники"],
    ["FRIEND",   5, "Друзья"]
);
