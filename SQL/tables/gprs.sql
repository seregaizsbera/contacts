DROP TABLE gprs;

BEGIN;

CREATE TABLE gprs (
    id int4 NOT NULL
            DEFAULT nextval('calls_id_seq'::text),
    moment timestamp NOT NULL,
    direction int4 NOT NULL
                   REFERENCES call_directions(id)
				   ON DELETE RESTRICT
				   ON UPDATE RESTRICT,
    url int4 NOT NULL
             REFERENCES gprs_urls(id)
	     ON DELETE RESTRICT
	     ON UPDATE RESTRICT,
    traffic numeric NOT NULL,
    price numeric NOT NULL,
    note text CHECK (note != ''),             
    PRIMARY KEY (id)
);

REVOKE ALL ON gprs FROM PUBLIC;
GRANT SELECT, INSERT, UPDATE, DELETE ON gprs TO apacheagent;
GRANT SELECT, INSERT, UPDATE, DELETE ON gprs TO j2eeagent;

COMMIT;
