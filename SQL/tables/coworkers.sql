DROP TABLE coworkers;

BEGIN;

CREATE TABLE coworkers (
    person int4 NOT NULL
                REFERENCES persons(id)
    	        ON DELETE RESTRICT
	            ON UPDATE RESTRICT,
    job text NOT NULL
             CHECK (job != ''),
    administration text CHECK (administration != ''),
    department text CHECK (department != ''),
    post text CHECK (post != ''),
    note text CHECK (note != ''),
    PRIMARY KEY (person)
);

COMMENT ON TABLE coworkers IS 'Информация о сослуживцах';
COMMENT ON COLUMN coworkers.person IS 'Идентификатор личности';
COMMENT ON COLUMN coworkers.job IS 'Место работы';
COMMENT ON COLUMN coworkers.administration IS 'Управление';
COMMENT ON COLUMN coworkers.department IS 'Отдел';
COMMENT ON COLUMN coworkers.post IS 'Должность';
COMMENT ON COLUMN coworkers.note IS 'Дополнительная информация';

REVOKE ALL ON coworkers FROM PUBLIC;
REVOKE ALL ON coworkers FROM j2eeagent;
REVOKE ALL ON coworkers FROM apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON coworkers TO apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON coworkers TO j2eeagent;

COMMIT;
