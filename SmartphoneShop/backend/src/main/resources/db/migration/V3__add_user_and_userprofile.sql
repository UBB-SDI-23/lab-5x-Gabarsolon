CREATE TABLE "user" (
                        id SERIAL PRIMARY KEY,
                        username VARCHAR(255) UNIQUE,
                        password VARCHAR(255)
);

CREATE TABLE UserProfile(
                            id SERIAL PRIMARY KEY,
                            bio VARCHAR(255),
                            location VARCHAR(255),
                            birthday DATE,
                            gender VARCHAR(255),
                            marital_status VARCHAR(255),
                            user_id BIGINT REFERENCES "user"(id)
);

ALTER TABLE "user"
    ADD COLUMN user_profile BIGINT REFERENCES userprofile(id)