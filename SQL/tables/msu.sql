DROP TABLE msu;

BEGIN;

CREATE TABLE msu (
    person int4 NOT NULL
                REFERENCES persons(id)
                ON DELETE RESTRICT
	        ON UPDATE RESTRICT,
    graduate date,
    department int NOT NULL
                   REFERENCES msu_departments(id)
		   ON DELETE RESTRICT
		   ON UPDATE RESTRICT,
    hospice boolean NOT NULL,
    subfaculty text CHECK (subfaculty != ''),
    note text CHECK (note != ''),
    PRIMARY KEY (person)
);

COMMENT ON TABLE msu IS 'Информациях о личностях, имеющих отношение к МГУ';
COMMENT ON COLUMN msu.person IS 'Идентификатор личности';
COMMENT ON COLUMN msu.graduate IS 'Дата поступления (Год)';
COMMENT ON COLUMN msu.department IS 'Идентификатор факультета';
COMMENT ON COLUMN msu.hospice IS 'Признак проживания в общежитии';
COMMENT ON COLUMN msu.subfaculty IS 'Кафедра';
COMMENT ON COLUMN msu.note IS 'Дополнительная информация';

REVOKE ALL ON msu FROM PUBLIC;
REVOKE ALL ON msu FROM j2eeagent;
REVOKE ALL ON msu FROM apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON msu TO apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON msu TO j2eeagent;

COMMIT;
