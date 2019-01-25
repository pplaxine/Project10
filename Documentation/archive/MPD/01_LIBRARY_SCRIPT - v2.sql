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

CREATE SEQUENCE public.book_genre_id_seq;

CREATE TABLE public.book_genre (
                id INTEGER NOT NULL DEFAULT nextval('public.book_genre_id_seq'),
                name VARCHAR(50) NOT NULL,
                CONSTRAINT book_genre_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.book_genre_id_seq OWNED BY public.book_genre.id;

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
                book_genre_id INTEGER NOT NULL,
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

ALTER TABLE public.book ADD CONSTRAINT book_genre_book_fk
FOREIGN KEY (book_genre_id)
REFERENCES public.book_genre (id)
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