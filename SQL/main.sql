-- Заведение пользователей (лучше завести заранее)
-- \i access/users.sql

-- Встроенный язык Perl и вспомогательные функции
\i system/perl.sql
\i system/plpgsql.sql
\i system/functions.sql

-- Табличные срезы системных данных
\i views/functions.sql
\i views/languages.sql
\i views/operators.sql

-- НСИ
\i tables/call_directions.sql
\i tables/call_expenses_kinds.sql
\i tables/call_types.sql
\i tables/genders.sql
\i tables/gprs_urls.sql
\i tables/months.sql
\i tables/msu_departments.sql
\i tables/phone_types.sql
\i tables/properties.sql
\i tables/psgm.sql
\i tables/reports.sql
\i tables/supply_kinds.sql
\i tables/supply_property_forms.sql
\i tables/inquiry_modes.sql
\i tables/inquiry.sql

-- Заполнение НСИ
\i content/call_directions.sql
\i content/gprs_urls.sql
\i content/msu_departments.sql
\i content/phone_types.sql
\i content/properties.sql
\i content/supply_kinds.sql
\i content/inquiry.sql

-- Основные таблицы
\i tables/persons.sql
\i tables/supplies.sql
\i tables/phones.sql
\i tables/emails.sql
\i tables/person_phones.sql
\i tables/supply_phones.sql
\i tables/person_emails.sql
\i tables/supply_emails.sql
\i tables/birthdays.sql
\i tables/call_reports.sql
\i tables/call_expenses.sql
\i tables/call_pays.sql
\i tables/calls.sql
\i tables/calls_buffer.sql
\i tables/gprs.sql
\i tables/gprs_buffer.sql
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
\i views/calls_full.sql
\i views/gprs_full.sql
