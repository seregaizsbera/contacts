DROP FUNCTION unique_basic(int4, int4, boolean);
DROP FUNCTION text(date);

BEGIN;

CREATE FUNCTION unique_basic(int4, int4, boolean)
    RETURNS text
    AS 'return $_[2] eq "t" ? "$_[0]G" : "$_[0]H$_[1]"'
    LANGUAGE 'perl';

CREATE FUNCTION text(date)
    RETURNS text
    AS 'SELECT to_char($1, ''yyyy-MM-dd'')'
    LANGUAGE 'sql';

COMMENT ON FUNCTION unique_basic(int4, int4, boolean) IS 'Возвращает значение, одинаковое для двух основных объектов одного человека';
COMMENT ON FUNCTION text(date) IS 'Преобразование даты в текст';

COMMIT;
