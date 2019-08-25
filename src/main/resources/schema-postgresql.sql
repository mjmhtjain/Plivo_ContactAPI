--DROP TABLE IF EXISTS cities;
--CREATE TABLE cities(id serial PRIMARY KEY, name VARCHAR(255), population

-- SEQ:
DROP SEQUENCE IF EXISTS "User_ID_seq" CASCADE;
CREATE SEQUENCE "User_ID_seq"
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

DROP SEQUENCE IF EXISTS "Contact_ID_seq" CASCADE;
CREATE SEQUENCE "Contact_ID_seq"
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

-- Table: public."User"

DROP TABLE IF EXISTS "user" CASCADE;

CREATE TABLE "user"
(
    "id" integer NOT NULL DEFAULT nextval('"User_ID_seq"'::regclass),
    "firstname" text COLLATE pg_catalog."default" NOT NULL,
    "lastname" text COLLATE pg_catalog."default" NOT NULL,
    "username" text COLLATE pg_catalog."default" NOT NULL,
    "password" text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "User_pkey" PRIMARY KEY ("id")
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

-- Table: public."Contact"
DROP TABLE IF EXISTS "contact" CASCADE;

CREATE TABLE "contact"
(
    "id" integer NOT NULL DEFAULT nextval('"Contact_ID_seq"'::regclass),
    "name" text COLLATE pg_catalog."default" NOT NULL,
    "emailaddress" text COLLATE pg_catalog."default" NOT NULL,
    "phonenumber" numeric(10,0) NOT NULL,
    "user_id" integer NOT NULL,
    CONSTRAINT "Contact_pkey" PRIMARY KEY ("id"),
    FOREIGN KEY ("user_id") REFERENCES "user" ("id")
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;



