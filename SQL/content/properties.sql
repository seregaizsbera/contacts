INSERT INTO properties (name, value, format, type, maker, note) VALUES
    ('last_birthdays_check', '17.07.1980', 'dd.MM.yyyy', 'java.util.Date', 'su.sergey.contacts.properties.impl.DatePropertyMaker', 'Дата последней проверки информации о ближайших днях рождения');
INSERT INTO properties (name, value, format, type, maker, note) VALUES
    ('report_folder', '/tmp', '^/(\w+/)+$', 'java.io.File', null, 'Каталог для размещения отчетов');
