BEGIN;

DELETE FROM addresses;
DELETE FROM icqs;
DELETE FROM person_phones;
DELETE FROM person_emails;
DELETE FROM birthdays;
DELETE FROM coworkers;
DELETE FROM msu;
DELETE FROM shnip;
DELETE FROM relatives;
DELETE FROM friends;
DELETE FROM supply_phones;
DELETE FROM supply_emails;
DELETE FROM phones;
DELETE FROM emails;
DELETE FROM supplies;
DELETE FROM persons;
DELETE FROM call_expenses;
DELETE FROM call_pays;
DELETE FROM call_reports;
DELETE FROM calls;
DELETE FROM gprs;
DELETE FROM queries;

SELECT setval('call_expenses_id_seq', 1);
SELECT setval('call_pays_id_seq', 1);
SELECT setval('call_reports_id_seq', 1);
SELECT setval('calls_id_seq', 1);
SELECT setval('gprs_id_seq', 1);
SELECT setval('emails_id_seq', 1);
SELECT setval('persons_id_seq', 1);
SELECT setval('phones_id_seq', 1);
SELECT setval('queries_id_seq', 1);
SELECT setval('supplies_id_seq', 1);

COMMIT;
