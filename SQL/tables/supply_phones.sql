DROP TABLE supply_phones;

BEGIN;

CREATE TABLE supply_phones (
    supply int4 NOT NULL
                REFERENCES supplies(id)
                ON DELETE RESTRICT
                ON UPDATE RESTRICT,
    phone int4 NOT NULL
               REFERENCES phones(id)
               ON DELETE RESTRICT
               ON UPDATE RESTRICT,
    PRIMARY KEY (supply, phone)
);

COMMENT ON TABLE supply_phones IS 'Таблица принадлежности телефонов предприятиям и организациям';
COMMENT ON COLUMN supply_phones.supply IS 'Идентификатор организации';
COMMENT ON COLUMN supply_phones.phone IS 'Идентификатор телефона';

REVOKE ALL ON supply_phones FROM PUBLIC;
REVOKE ALL ON supply_phones FROM j2eeagent;
REVOKE ALL ON supply_phones FROM apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON supply_phones TO apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON supply_phones TO j2eeagent;

COMMIT;
