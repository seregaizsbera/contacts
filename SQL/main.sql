-- \i createdb.sql

-- Заведение пользователей
\i access/users.sql

-- Встроенный язык Perl
\i system/perl.sql

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

-- Заполнение НСИ
\i content/phone_types.sql
\i content/supply_kinds.sql
\i content/msu_departments.sql
\i content/call_directions.sql
\i content/call_types.sql
\i content/gprs_urls.sql

-- Основные таблицы
\i tables/persons.sql
\i tables/phones.sql
\i tables/birthdays.sql
\i tables/supplies.sql
\i tables/supply_phones.sql
\i tables/emails.sql
\i tables/call_reports.sql
\i tables/calls.sql
\i tables/calls_pays.sql
\i tables/gprs.sql
\i tables/icqs.sql
\i tables/msu.sql
\i tables/friends.sql
\i tables/shnip.sql
\i tables/coworkers.sql
