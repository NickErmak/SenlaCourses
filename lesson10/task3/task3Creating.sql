drop database if exists libraryErmak;

create database libraryErmak;

use libraryErmak;

CREATE TABLE orderBook (
    id INT AUTO_INCREMENT,
    bookId INT NOT NULL,
    orderId INT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE request (
    id INT AUTO_INCREMENT,
    bookId INT NOT NULL,
    date DATE NOT NULL,
    status ENUM('processing', 'completed', 'cancelled'),
    PRIMARY KEY (id)
);

CREATE TABLE book (
    id INT AUTO_INCREMENT,
    title VARCHAR(15) NOT NULL,
    publicationDate DATE NOT NULL,
    price DOUBLE,
    description VARCHAR(100),
    onStock BOOLEAN,
    orderBookId INT,
    requestId INT,
    PRIMARY KEY (id),
    FOREIGN KEY (orderBookId)
        REFERENCES orderBook (id),
    FOREIGN KEY (requestId)
        REFERENCES request (id)
);

CREATE TABLE orders (
    id INT AUTO_INCREMENT,
    name VARCHAR(10) NOT NULL,
    date DATE NOT NULL,
    totalAmount DOUBLE,
    orderBookId INT,
    status ENUM('processing', 'completed', 'cancelled'),
    PRIMARY KEY (id),
    FOREIGN KEY (orderBookId)
        REFERENCES orderBook (id)
);

