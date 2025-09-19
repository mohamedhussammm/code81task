-- Create database if it doesn't exist
CREATE DATABASE IF NOT EXISTS code81;
USE code81;

-- Create tables
CREATE TABLE IF NOT EXISTS system_user (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role ENUM('ADMIN', 'LIBRARIAN', 'STAFF') NOT NULL
);

CREATE TABLE IF NOT EXISTS user_activity (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    action VARCHAR(255) NOT NULL,
    timestamp DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    details TEXT,
    FOREIGN KEY (user_id) REFERENCES system_user(id)
);

-- Insert default admin user (password: admin)
INSERT IGNORE INTO system_user (username, password, role) VALUES 
('admin', '$2a$10$rOzZJN8.7kz/3Q4qQe8J.u6c6V7k8zQc8VzQc8VzQc8VzQc8VzQc8', 'ADMIN'),
('librarian', '$2a$10$rOzZJN8.7kz/3Q4qQe8J.u6c6V7k8zQc8VzQc8VzQc8VzQc8VzQc8', 'LIBRARIAN'),
('staff', '$2a$10$rOzZJN8.7kz/3Q4qQe8J.u6c6V7k8zQc8VzQc8VzQc8VzQc8VzQc8', 'STAFF');