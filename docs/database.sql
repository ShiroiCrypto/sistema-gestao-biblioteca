-- Banco: sistema_biblioteca (XAMPP / phpMyAdmin)
-- Execute este script no MySQL antes de rodar a aplicação.

CREATE DATABASE IF NOT EXISTS sistema_biblioteca
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

USE sistema_biblioteca;

CREATE TABLE IF NOT EXISTS livros (
  id INT NOT NULL AUTO_INCREMENT,
  titulo VARCHAR(255) NOT NULL,
  autor VARCHAR(255) NOT NULL,
  ano VARCHAR(20) DEFAULT NULL,
  categoria VARCHAR(100) DEFAULT NULL,
  status VARCHAR(50) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
