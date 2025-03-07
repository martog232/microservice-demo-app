CREATE TABLE users (
                       id BIGINT PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       country_id BIGINT NOT NULL
);

CREATE SEQUENCE user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE users ALTER COLUMN id SET DEFAULT nextval('user_id_seq');

ALTER TABLE users
    ADD CONSTRAINT fk_user_country
        FOREIGN KEY (country_id)
            REFERENCES countries(id);
