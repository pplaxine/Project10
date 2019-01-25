 /*TRANSACTION START*/
BEGIN TRANSACTION;

/*DROP ALL TABLES*/
DROP TABLE IF EXISTS book CASCADE;
DROP TABLE IF EXISTS borrowing CASCADE;
DROP TABLE IF EXISTS library CASCADE;
DROP TABLE IF EXISTS library_address CASCADE;
DROP TABLE IF EXISTS staff_account CASCADE;
DROP TABLE IF EXISTS user_account CASCADE;
DROP TABLE IF EXISTS user_address CASCADE;
DROP TYPE IF EXISTS GENRE;


/*INSER ALL TABLES*/
CREATE SEQUENCE public.user_address_id_seq;

CREATE TABLE public.user_address (
                id INTEGER NOT NULL DEFAULT nextval('public.user_address_id_seq'),
                street_number VARCHAR(10) NOT NULL,
                street_name VARCHAR(200) NOT NULL,
                city VARCHAR(200) NOT NULL,
                post_code NUMERIC(5) NOT NULL,
                CONSTRAINT user_address_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.user_address_id_seq OWNED BY public.user_address.id;

CREATE SEQUENCE public.user_account_id_seq;

CREATE TABLE public.user_account (
                id INTEGER NOT NULL DEFAULT nextval('public.user_account_id_seq'),
                user_member_id VARCHAR(20) NOT NULL,
                password VARCHAR(50),
                access VARCHAR NOT NULL,
                firstname VARCHAR(50) NOT NULL,
                surename VARCHAR(50) NOT NULL,
                user_address_id INTEGER NOT NULL,
                email VARCHAR(100) NOT NULL,
                phone_number NUMERIC(15),
                blocked_account BOOLEAN NOT NULL,
                CONSTRAINT user_account_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.user_account_id_seq OWNED BY public.user_account.id;

CREATE UNIQUE INDEX user_account_email_unique_idx
 ON public.user_account
 ( email );

CREATE UNIQUE INDEX user_account_member_id_unique_idx
 ON public.user_account
 ( user_member_id );

CREATE SEQUENCE public.library_address_id_seq;

CREATE TABLE public.library_address (
                id INTEGER NOT NULL DEFAULT nextval('public.library_address_id_seq'),
                street_number VARCHAR(10) NOT NULL,
                street_name VARCHAR(200) NOT NULL,
                city VARCHAR(200) NOT NULL,
                post_code NUMERIC(5) NOT NULL,
                CONSTRAINT library_address_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.library_address_id_seq OWNED BY public.library_address.id;

CREATE SEQUENCE public.library_id_seq;

CREATE TABLE public.library (
                id INTEGER NOT NULL DEFAULT nextval('public.library_id_seq'),
                name VARCHAR(100) NOT NULL,
                phone_number NUMERIC(15) NOT NULL,
                library_address_id INTEGER NOT NULL,
                CONSTRAINT library_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.library_id_seq OWNED BY public.library.id;

CREATE SEQUENCE public.staff_account_id_seq;

CREATE TABLE public.staff_account (
                id INTEGER NOT NULL DEFAULT nextval('public.staff_account_id_seq'),
                login_name VARCHAR(20),
                password VARCHAR(50),
                access VARCHAR NOT NULL,
                firstname VARCHAR(50) NOT NULL,
                surename VARCHAR(50) NOT NULL,
                account_activated BOOLEAN NOT NULL,
                library_id INTEGER,
                CONSTRAINT staff_account_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.staff_account_id_seq OWNED BY public.staff_account.id;

CREATE UNIQUE INDEX staff_account_login_unique_idx
 ON public.staff_account
 ( login_name );


CREATE SEQUENCE public.book_id_seq;

CREATE TABLE public.book (
                id INTEGER NOT NULL DEFAULT nextval('public.book_id_seq'),
                name VARCHAR(300) NOT NULL,
                author VARCHAR(200) NOT NULL,
                summary VARCHAR(2000) NOT NULL,
                available BOOLEAN NOT NULL,
                book_genre VARCHAR NOT NULL,
                library_id INTEGER NOT NULL,
                CONSTRAINT book_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.book_id_seq OWNED BY public.book.id;

CREATE SEQUENCE public.borrowing_id_seq;

CREATE TABLE public.borrowing (
                id INTEGER NOT NULL DEFAULT nextval('public.borrowing_id_seq'),
                start_date TIMESTAMP NOT NULL,
                supposed_end_date TIMESTAMP NOT NULL,
                second_supposed_end_date TIMESTAMP,
                effective_End_Date TIMESTAMP,
                extended BOOLEAN NOT NULL,
                book_id INTEGER NOT NULL,
                user_account_id INTEGER NOT NULL,
                CONSTRAINT borrowing_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.borrowing_id_seq OWNED BY public.borrowing.id;

ALTER TABLE public.user_account ADD CONSTRAINT user_address_user_account_fk
FOREIGN KEY (user_address_id)
REFERENCES public.user_address (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.borrowing ADD CONSTRAINT user_account_borrowing_fk
FOREIGN KEY (user_account_id)
REFERENCES public.user_account (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.library ADD CONSTRAINT library_address_library_fk
FOREIGN KEY (library_address_id)
REFERENCES public.library_address (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.staff_account ADD CONSTRAINT library_staff_account_fk
FOREIGN KEY (library_id)
REFERENCES public.library (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.book ADD CONSTRAINT library_book_fk
FOREIGN KEY (library_id)
REFERENCES public.library (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.borrowing ADD CONSTRAINT book_borrowing_fk
FOREIGN KEY (book_id)
REFERENCES public.book (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

/*DATA INJECTION*/
INSERT INTO library_address (street_number,street_name,city,post_code) VALUES ('42','Avenue Daumesnil','Paris',75012); 
INSERT INTO library_address (street_number,street_name,city,post_code) VALUES ('115','Rue de Bagnolet','Paris',75020);
INSERT INTO library_address (street_number,street_name,city,post_code) VALUES ('88','Bld de Port-Royal','Paris',
75005); 

INSERT INTO library (name,phone_number,library_address_id) VALUES ('Bibliothèque jeunesse Didero',0033156811070,1);
INSERT INTO library (name,phone_number,library_address_id) VALUES ('Médiathèque Marguerite Duras',0033155254910,2);
INSERT INTO library (name,phone_number,library_address_id) VALUES ('Bibliothèque municipale Rainer Maria Rilke',0033156811070,3);


INSERT INTO staff_account (login_name,password,access,firstname,surename,account_activated) VALUES ('AHerbert','test00','ADMIN_09','Adam','Herbert',TRUE);
INSERT INTO staff_account (login_name,password,access,firstname,surename,account_activated,library_id) VALUES ('VLaine','test01','USER_99','Victoria','Laine',TRUE,1);
INSERT INTO staff_account (login_name,password,access,firstname,surename,account_activated,library_id) VALUES ('JDoe','test02','USER_99','John','Doe',TRUE,1);
INSERT INTO staff_account (login_name,password,access,firstname,surename,account_activated,library_id) VALUES ('AWarner','test03','USER_99','Alice','Warner',TRUE,1);
INSERT INTO staff_account (login_name,password,access,firstname,surename,account_activated,library_id) VALUES ('PBean','test04','USER_99','Paul','Bean',TRUE,2);
INSERT INTO staff_account (login_name,password,access,firstname,surename,account_activated,library_id) VALUES ('JSmith','test05','USER_99','James','Smith',TRUE,2);
INSERT INTO staff_account (login_name,password,access,firstname,surename,account_activated,library_id) VALUES ('SLone','test06','USER_99','Samuel','Lone',TRUE,3);
INSERT INTO staff_account (login_name,password,access,firstname,surename,account_activated,library_id) VALUES ('ASpooner','test07','USER_99','Adriana','Spooner',TRUE,3);


INSERT INTO book (name, author, summary, book_genre, available, library_id) VALUES ('Roméo et Juliette','Willian Shakespeare','résumé du livre...','TRAGEDY',false,1);
INSERT INTO book (name, author, summary, book_genre, available, library_id) VALUES ('Roméo et Juliette','Willian Shakespeare','résumé du livre...','TRAGEDY',false,1);
INSERT INTO book (name, author, summary, book_genre, available, library_id) VALUES ('Roméo et Juliette','Willian Shakespeare','résumé du livre...','TRAGEDY',false,1);
INSERT INTO book (name, author, summary, book_genre, available, library_id) VALUES ('Roméo et Juliette','Willian Shakespeare','résumé du livre...','TRAGEDY',false,1);
INSERT INTO book (name, author, summary, book_genre, available, library_id) VALUES ('Roméo et Juliette','Willian Shakespeare','résumé du livre...','TRAGEDY',true,1);
INSERT INTO book (name, author, summary, book_genre, available, library_id) VALUES ('Roméo et Juliette','Willian Shakespeare','résumé du livre...','TRAGEDY',true,1);
INSERT INTO book (name, author, summary, book_genre, available, library_id) VALUES ('Roméo et Juliette','Willian Shakespeare','résumé du livre...','TRAGEDY',true,1);

INSERT INTO user_address (street_number,street_name,city,post_code) VALUES ('3bis','Rue léchevin','Paris',75011);
INSERT INTO user_address (street_number,street_name,city,post_code) VALUES ('18','Avenue Montaigne','Paris',75008);
INSERT INTO user_address (street_number,street_name,city,post_code) VALUES ('14',E'Rue d\'Antin','Paris',75002);

INSERT INTO user_account(user_member_id,password,access,firstname,surename,user_address_id,email,phone_number,blocked_account) VALUES ('JTille','test01','USER_99','Jean','Tille',1,'jeantille@orange.fr',0033145484575,False);
INSERT INTO user_account(user_member_id,access,firstname,surename,user_address_id,email,phone_number,blocked_account) VALUES ('MSegaux','USER_99','Madelaine','Segaux',2,'mady@orange.fr',003314541436,False);

INSERT INTO borrowing (start_date,supposed_end_date,second_supposed_end_date,effective_end_date, extended,book_id, user_account_id) VALUES ('2018-02-17 09:00:00','2018-02-23 18:00:00','2018-02-27 18:00:00','2018-02-26 18:00:00',true,1,1);
INSERT INTO borrowing (start_date,supposed_end_date, extended,book_id, user_account_id) VALUES ('2019-01-14 09:00:00','2019-01-15 09:00:00',false,4,2);
INSERT INTO borrowing (start_date,supposed_end_date, extended,book_id, user_account_id) VALUES ('2019-01-15 09:00:00','2019-01-23 18:00:00',false,2,2);
INSERT INTO borrowing (start_date,supposed_end_date,second_supposed_end_date, extended,book_id, user_account_id) VALUES ('2019-01-14 09:00:00','2019-01-15 09:00:00','2019-01-27 18:00:00',true,1,2);
INSERT INTO borrowing (start_date,supposed_end_date,second_supposed_end_date, extended,book_id, user_account_id) VALUES ('2019-01-15 09:00:00','2019-01-23 18:00:00','2019-01-27 18:00:00',true,3,1);

/*TRANSACTION END*/
COMMIT;