CREATE DATABASE IF NOT EXISTS confeitaria;

USE confeitaria;

CREATE TABLE IF NOT EXISTS bolo (
    id                  INT             AUTO_INCREMENT,
    nome                VARCHAR(100)    NOT NULL,
    descricao           VARCHAR(200)    NOT NULL,
    ingredientes        VARCHAR(200)    NOT NULL,
    formato             VARCHAR(20)     NOT NULL,
    preco               DECIMAL(4, 2)   NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS confeiteiro (
    id                  INT             AUTO_INCREMENT,
    nome                VARCHAR(100)    NOT NULL,
    email               VARCHAR(60)     NOT NULL,
    telefone            CHAR(11)        NOT NULL,
    sobre               VARCHAR(200)    NOT NULL,
    especialidade       VARCHAR(80)     NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS usuario (
    id                  INT             AUTO_INCREMENT,
    email               VARCHAR(60)     NOT NULL,
    senha               VARCHAR(15)     NOT NULL,
    PRIMARY KEY (id)
);