-- Заведение пользователей
\i access/users.sql

-- Встроенный язык Perl и вспомогательные функции
\i system/perl.sql
\i system/functions.sql

-- Табличные срезы системных данных
\i views/functions.sql
\i views/languages.sql
\i views/operators.sql

-- НСИ
\i tables/phone_types.sql
\i tables/supply_kinds.sql
\i tables/msu_departments.sql
\i tables/call_directions.sql
\i tables/call_types.sql
\i tables/gprs_urls.sql
\i tables/properties.sql
\i tables/genders.sql
\i tables/months.sql
\i tables/psgm.sql

-- Заполнение НСИ
\i content/phone_types.sql
\i content/supply_kinds.sql
\i content/msu_departments.sql
\i content/call_directions.sql
\i content/call_types.sql
\i content/gprs_urls.sql
\i content/properties.sql

-- Основные таблицы
\i tables/persons.sql
\i tables/supplies.sql
\i tables/phones.sql
\i tables/person_phones.sql
\i tables/birthdays.sql
\i tables/supply_phones.sql
\i tables/emails.sql
\i tables/person_emails.sql
\i tables/supply_phones.sql
\i tables/call_reports.sql
\i tables/calls.sql
\i tables/calls_pays.sql
\i tables/gprs.sql
\i tables/icqs.sql
\i tables/msu.sql
\i tables/friends.sql
\i tables/shnip.sql
\i tables/coworkers.sql
\i tables/addresses.sql
\i tables/relatives.sql
\i tables/queries.sql

-- Вспомогательные представления данных
\i views/shnippers.sql
