DROP PROCEDURAL LANGUAGE 'perl';
DROP FUNCTION plperl_call_handler();
DROP FUNCTION perl_test(int4, int4);

BEGIN;

CREATE FUNCTION plperl_call_handler()
    RETURNS opaque
    AS '/usr/local/lib/libplperl.so'
    LANGUAGE 'C';
    
CREATE TRUSTED PROCEDURAL LANGUAGE 'perl'
    HANDLER plperl_call_handler
    LANCOMPILER 'PL/Perl';
    
CREATE FUNCTION perl_test(int4, int4)
    RETURNS int4
    AS 'return $_[0] * $_[1];'
    LANGUAGE 'perl';

COMMIT;
