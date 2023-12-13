DROP SCHEMA IF EXISTS chess;
CREATE SCHEMA chess;

USE chess;

CREATE TABLE auth (
    id VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);
CREATE TABLE game (
    gameID INT NOT NULL AUTO_INCREMENT,
    whiteUsername VARCHAR(255),
    blackUsername VARCHAR(255),
    gameName VARCHAR(255) NOT NULL,
    game TEXT NOT NULL,
    PRIMARY KEY (gameID)
);
CREATE TABLE user (
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    UNIQUE (username),
    PRIMARY KEY (username)
);