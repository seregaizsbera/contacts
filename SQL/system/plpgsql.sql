DROP PROCEDURAL LANGUAGE 'plpgsql';
DROP FUNCTION plpgsql_call_handler();
DROP FUNCTION plpgsql_test(int4, int4);

BEGIN;

CREATE FUNCTION plpgsql_call_handler()
    RETURNS OPAQUE
    AS '/usr/lib/pgsql/plpgsql.so'
    LANGUAGE 'C';
    
CREATE TRUSTED PROCEDURAL LANGUAGE 'plpgsql'
    HANDLER plpgsql_call_handler
    LANCOMPILER 'PL/pgSQL';
    
CREATE FUNCTION plpgsql_test(int4, int4)
    RETURNS int4
    AS 'BEGIN RETURN $1 * $2; END;'
    LANGUAGE 'plpgsql';

COMMENT ON FUNCTION plpgsql_call_handler() IS 'Обработчик встроенных процедур на языке PL/pgSQL';
COMMENT ON FUNCTION plpgsql_test(int4, int4) IS 'Тестирование встроенного языка PL/pgSQL';

COMMIT;
