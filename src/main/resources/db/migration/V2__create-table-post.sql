CREATE TABLE Posts (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    created_in DATE NOT NULL,
    user_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES Users(id)
);
