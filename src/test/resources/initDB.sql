DROP TABLE IF EXISTS Books;

CREATE TABLE Books
(
    id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    publicationYear DATE
);