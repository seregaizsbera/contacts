\c sergey sergey

DROP DATABASE contacts;
CREATE DATABASE contacts;
COMMENT ON DATABASE contacts IS 'База данных людей, предприятий, организаций, их адресов, телефонов и звонков';

\c contacts sergey

\i main.sql
