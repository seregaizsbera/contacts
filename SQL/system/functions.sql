DROP FUNCTION unique_basic(int4, int4, boolean);

BEGIN;

CREATE FUNCTION unique_basic(int4, int4, boolean)
    RETURNS text
    AS 'return $_[2] eq "t" ? "$_[0]G" : "$_[0]H$_[1]"'
    LANGUAGE 'perl';

COMMENT ON FUNCTION unique_basic(int4, int4, boolean) IS 'Возвращает значение, одинаковое для двух основных объектов одного человека';

COMMIT;
