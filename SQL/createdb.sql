\c sergey sergey

DROP DATABASE contacts1;
CREATE DATABASE contacts1;
COMMENT ON DATABASE contacts1 IS 'База данных людей, предприятий, организаций, их адресов, телефонов и звонков';

\c contacts1 sergey

\i main.sql
