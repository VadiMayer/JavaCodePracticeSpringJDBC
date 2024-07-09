DROP TABLE IF EXISTS Books;

CREATE TABLE Books
(
    id INT PRIMARY KEY,
    title VARCHAR(40) NOT NULL,
    author VARCHAR(32) NOT NULL,
    publicationYear DATE
);