DROP TABLE shnip;

BEGIN;

CREATE TABLE shnip (
    person int4 NOT NULL
                REFERENCES persons(id)
	        ON DELETE RESTRICT
	        ON UPDATE RESTRICT,
    graduate date,
    form_letter text CHECK (form_letter != ''),
    form_leader int4 REFERENCES shnip(person)
		     ON DELETE SET NULL
		     ON UPDATE SET NULL,
    description text CHECK (description != ''),
    PRIMARY KEY (person)
);

COMMENT ON TABLE shnip IS 'Информациях о личностях, имеющих отношение к ШНИПу';
COMMENT ON COLUMN shnip.person IS 'Идентификатор личности';
COMMENT ON COLUMN shnip.graduate IS 'Дата выпуска (Год)';
COMMENT ON COLUMN shnip.form_letter IS 'Буква учебного класса';
COMMENT ON COLUMN shnip.form_leader IS 'Идентификатор классного руководителя';
COMMENT ON COLUMN shnip.description IS 'Дополнительная информация';

REVOKE ALL ON shnip FROM PUBLIC;
REVOKE ALL ON shnip FROM j2eeagent;
REVOKE ALL ON shnip FROM apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON shnip TO apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON shnip TO j2eeagent;

COMMIT;
