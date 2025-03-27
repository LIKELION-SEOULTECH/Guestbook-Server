CREATE DATABASE IF NOT EXISTS guestbook;
USE guestbook;

DROP TABLE IF EXISTS posts;

CREATE TABLE posts
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_name  VARCHAR(255),
    password   VARCHAR(255),
    content    TEXT,
    emotion    ENUM ('HORROR', 'SURPRISE', 'ANGER', 'SORROW', 'NEUTRALITY', 'HAPPINESS', 'AVERSION'),
    created_at DATETIME,
    updated_at DATETIME
);
