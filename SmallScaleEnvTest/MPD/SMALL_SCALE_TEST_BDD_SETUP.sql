 /*TRANSACTION START*/
BEGIN TRANSACTION;

/*DROP ALL TABLES*/
DROP TABLE IF EXISTS user_test CASCADE;
DROP TABLE IF EXISTS address CASCADE;

/*INSER ALL TABLES*/

CREATE SEQUENCE public.user_test_id_seq;

CREATE TABLE public.user_test (
                id INTEGER NOT NULL DEFAULT nextval('public.user_test_id_seq'),
                firstname VARCHAR NOT NULL,
                lastname VARCHAR NOT NULL,
                age INTEGER NOT NULL,
                CONSTRAINT user_test_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.user_test_id_seq OWNED BY public.user_test.id;

CREATE SEQUENCE public.address_id_seq;

CREATE TABLE public.address (
                id INTEGER NOT NULL DEFAULT nextval('public.address_id_seq'),
                street_number VARCHAR NOT NULL,
                street_name VARCHAR NOT NULL,
                city VARCHAR NOT NULL,
                post_code NUMERIC(5) NOT NULL,
                user_test_id INTEGER NOT NULL,
                CONSTRAINT address_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.address_id_seq OWNED BY public.address.id;

ALTER TABLE public.address ADD CONSTRAINT user_address_fk
FOREIGN KEY (user_test_id)
REFERENCES public.user_test (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

/*DATA INJECTION*/

INSERT INTO user_test (firstname,lastname,age) VALUES ('Paul','Faure',38);
INSERT INTO user_test (firstname,lastname,age) VALUES ('Jacques','Dufour',42);
INSERT INTO user_test (firstname,lastname,age) VALUES ('Adeline','Duo',25);

INSERT INTO address (street_number,street_name,city,post_code,user_test_id) VALUES ('8 ter','rue de Paris','Lyon',69001,1);
INSERT INTO address (street_number,street_name,city,post_code,user_test_id) VALUES ('17 bis','avenue Montaigne','Paris',75008,1);
INSERT INTO address (street_number,street_name,city,post_code,user_test_id) VALUES ('83','rue Saint Bernard','Paris',75011,2);
INSERT INTO address (street_number,street_name,city,post_code,user_test_id) VALUES ('17','rue de Foo','Paris',75013,3);


/*TRANSACTION END*/
COMMIT;