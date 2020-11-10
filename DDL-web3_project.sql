CREATE SCHEMA web3_project_r0750976; -- replace r0750976 with your studentnr

GRANT ALL ON SCHEMA web3_project_r0750976 TO lector WITH GRANT OPTION; -- replace r0750976 with your studentnr
GRANT ALL ON SCHEMA web3_project_r0750976 TO r0750976; -- replace r0750976 with your studentnr
GRANT ALL ON SCHEMA web3_project_r0750976 TO local_r0750976; -- replace r0750976 with your studentnr

SET search_path TO web3_project_r0750976; -- replace r0750976 with your studentnr

DROP TABLE IF EXISTS web3_project_r0750976.gebruiker; -- replace r0750976 with your studentnr

CREATE TABLE web3_project_r0750976.gebruiker ( -- replace r0750976 with your studentnr
                                                id SERIAL PRIMARY KEY,
                                                voornaam character varying(32) NOT NULL,
                                                familienaam character varying(32) NOT NULL,
                                                emailadres character varying(64) NOT NULL,
                                                wachtwoord character varying(32) NOT NULL,
                                                rol character varying(16) DEFAULT 'gebruiker' NOT NULL
);

GRANT ALL ON TABLE web3_project_r0750976.gebruiker TO lector WITH GRANT OPTION; -- replace r0750976 with your studentnr
GRANT ALL ON TABLE web3_project_r0750976.gebruiker TO r0750976; -- replace r0750976 with your studentnr
GRANT ALL ON TABLE web3_project_r0750976.gebruiker TO local_r0750976; -- replace r0750976 with your studentnr

DROP TABLE IF EXISTS web3_project_r0750976.bezoek; -- replace r0750976 with your studentnr

CREATE TABLE web3_project_r0750976.bezoek ( -- replace r0750976 with your studentnr
                                             id SERIAL PRIMARY KEY,
                                             person_id integer NULL,
                                             voornaam character varying(32) NOT NULL,
                                             familienaam character varying(32) NOT NULL,
                                             emailadres character varying(64) NOT NULL,
                                             telefoonnummer character varying(15) NOT NULL,
                                             date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

GRANT ALL ON TABLE web3_project_r0750976.bezoek TO lector; -- replace r0750976 with your studentnr
GRANT ALL ON TABLE web3_project_r0750976.bezoek TO r0750976; -- replace r0750976 with your studentnr
GRANT ALL ON TABLE web3_project_r0750976.bezoek TO local_r0750976; -- replace r0750976 with your studentnr

GRANT ALL ON SEQUENCE web3_project_r0750976.bezoek_id_seq TO lector WITH GRANT OPTION;
GRANT ALL ON SEQUENCE web3_project_r0750976.bezoek_id_seq TO local_r0750976;

GRANT ALL ON SEQUENCE web3_project_r0750976.gebruiker_id_seq TO lector WITH GRANT OPTION;
GRANT ALL ON SEQUENCE web3_project_r0750976.gebruiker_id_seq TO local_r0750976;