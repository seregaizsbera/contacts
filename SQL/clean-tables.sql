begin;

delete from addresses;
delete from icqs;
delete from person_phones;
delete from person_emails;
delete from birthdays;
delete from coworkers;
delete from msu;
delete from shnip;
delete from relatives;
delete from friends;
delete from supply_phones;
delete from supply_emails;
delete from phones;
delete from emails;
delete from supplies;
delete from persons;
delete from call_expenses;
delete from call_pays;
delete from call_reports;
delete from calls;
delete from gprs;
delete from queries;

select setval('call_expenses_id_seq', 1);
select setval('call_pays_id_seq', 1);
select setval('call_reports_id_seq', 1);
select setval('calls_id_seq', 1);
select setval('gprs_id_seq', 1);
select setval('emails_id_seq', 1);
select setval('persons_id_seq', 1);
select setval('phones_id_seq', 1);
select setval('queries_id_seq', 1);
select setval('supplies_id_seq', 1);

commit;
