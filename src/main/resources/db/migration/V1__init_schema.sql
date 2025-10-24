-- V1__init_schema.sql
-- Flyway baseline schema for the Todo backend

-- USERS TABLE
CREATE TABLE users (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    user_role VARCHAR(50),
    CONSTRAINT chk_user_role CHECK (user_role IN ('ADMIN', 'USER'))
);

-- TODOS TABLE
CREATE TABLE todos (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    text VARCHAR(1000) NOT NULL,
    completed BOOLEAN NOT NULL DEFAULT FALSE,
    priority_tag VARCHAR(20) NOT NULL DEFAULT 'MEDIUM',
    user_id BIGINT NOT NULL,
    CONSTRAINT fk_todo_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

-- ADMINS TABLE
CREATE TABLE admin (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY
);

-- INDEXES
CREATE INDEX idx_todos_user_id ON todos(user_id);