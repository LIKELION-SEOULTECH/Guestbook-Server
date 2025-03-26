CREATE DATABASE IF NOT EXISTS guestbook;

USE guestbook;

CREATE TABLE IF NOT EXISTS posts (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     user_name VARCHAR(255),
                                     password VARCHAR(255),
                                     content TEXT,
                                     emotion VARCHAR(50),
                                     created_at DATETIME,
                                     updated_at DATETIME
);

ALTER TABLE posts MODIFY id BIGINT AUTO_INCREMENT;