DROP FUNCTION unique_basic(int4, int4, boolean);
DROP FUNCTION text(date);
DROP FUNCTION phone_norm(text);
DROP FUNCTION set_insert_time();
DROP FUNCTION set_update_time();

BEGIN;

CREATE FUNCTION unique_basic(int4, int4, boolean)
    RETURNS text
    AS 'return $_[2] eq "t" ? "$_[0]G" : "$_[0]H$_[1]"'
    LANGUAGE 'perl';

CREATE FUNCTION text(date)
    RETURNS text
    AS 'SELECT to_char($1, ''yyyy-MM-dd'')'
    LANGUAGE 'sql';

CREATE FUNCTION phone_norm(text)
    RETURNS text
    AS '
        if ($_[0] =~ /^8095(\\d\\d\\d)(\\d\\d)(\\d\\d)$/) {
	    return "$1-$2-$3";
        } elsif ($_[0] =~ /^8(\\d\\d\\d)(\\d\\d\\d)(\\d\\d)(\\d\\d)$/) {
	    return "($1)-$2-$3-$4";
	} elsif ($_[0] =~ /^(\\d\\d\\d)(\\d\\d)(\\d\\d)$/) {
	    return "$1-$2-$3";
	} else {
	    return $_[0];
	}
    '
    LANGUAGE 'perl';

CREATE FUNCTION set_insert_time()
    RETURNS OPAQUE
    AS '
        BEGIN
	    new.insert_time := now();
	    new.update_time := new.insert_time;
	    return new;
	END;
    '
    LANGUAGE 'plpgsql';
    
CREATE FUNCTION set_update_time()
    RETURNS OPAQUE
    AS '
        BEGIN
	    new.update_time := now();
	    return new;
	END;
    '
    LANGUAGE 'plpgsql';

CREATE FUNCTION text(numeric(30, 6))
    RETURNS text
    AS 'return $_[0];'
    LANGUAGE 'perl';
    
COMMENT ON FUNCTION unique_basic(int4, int4, boolean) IS 'Возвращает значение, одинаковое для двух основных объектов одного человека';
COMMENT ON FUNCTION text(date) IS 'Преобразование даты в текст';
COMMENT ON FUNCTION phone_norm(text) IS 'Нормализация номеров телефонов в некоторых форматах';
COMMENT ON FUNCTION set_insert_time() IS 'Установка в триггере времени заведения записи';
COMMENT ON FUNCTION set_update_time() IS 'Установка в триггере времени последнего обновления записи';
COMMENT ON FUNCTION text(numeric(30, 6)) IS 'Преобразование в текст типа, используемого для представления денежных сумм';

COMMIT;
