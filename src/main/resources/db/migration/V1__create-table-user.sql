CREATE TABLE Users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL CHECK (CHAR_LENGTH(name) >= 3),
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    UNIQUE (email)
);
