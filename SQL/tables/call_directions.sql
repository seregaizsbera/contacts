DROP TABLE call_directions;
DROP SEQUENCE call_directions_id_seq;

BEGIN;

CREATE SEQUENCE call_directions_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CYCLE;

CREATE TABLE call_directions (
    id int4 NOT NULL
            DEFAULT nextval('call_directions_id_seq'::text),
    name text NOT NULL
              CHECK (name != '')
		      UNIQUE,
    PRIMARY KEY (id)
);

COMMENT ON TABLE call_directions IS 'Возможные направления звонков';
COMMENT ON COLUMN call_directions.id IS 'Идентификатор направления звонка';
COMMENT ON COLUMN call_directions.name IS 'Направление звонка';
COMMENT ON SEQUENCE call_directions_id_seq IS 'Генератор идентификаторов направлений звонков';

REVOKE ALL ON call_directions, call_directions_id_seq FROM PUBLIC;
REVOKE ALL ON call_directions, call_directions_id_seq FROM j2eeagent;
REVOKE ALL ON call_directions, call_directions_id_seq FROM apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON call_directions TO apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON call_directions TO j2eeagent;
GRANT SELECT, UPDATE ON call_directions_id_seq TO apacheagent;
GRANT SELECT, UPDATE ON call_directions_id_seq TO j2eeagent;

COMMIT;
