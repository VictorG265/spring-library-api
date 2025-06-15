drop  database if exists library;
create database library DEFAULT CHARACTER SET utf8;

use library;

DROP TABLE if exists books ;
DROP TABLE if exists readers;
DROP TABLE if exists loans ;

CREATE TABLE `library`.`books` (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    genre VARCHAR(100),
    quantity INT NOT NULL
);

INSERT INTO books (title, author, genre, quantity) VALUES
('Властелин колец', 'Дж. Р. Р. Толкин', 'Фэнтези', 5),
('1984', 'Джордж Оруэлл', 'Антиутопия', 3),
('Преступление и наказание', 'Ф. М. Достоевский', 'Классика', 4);
  
CREATE TABLE `library`.`readers` (
      id SERIAL PRIMARY KEY,
      name VARCHAR(255) NOT NULL,
      email VARCHAR(255) UNIQUE NOT NULL
  );
    
INSERT INTO readers (name, email) VALUES
('Иван Иванов', 'ivan@example.com'),
('Мария Петрова', 'maria@example.com');

CREATE TABLE `library`.`loans` (
    id SERIAL PRIMARY KEY,
    book_id BIGINT UNSIGNED NOT NULL,
    reader_id BIGINT UNSIGNED NOT NULL,
    issue_date DATE NOT NULL,
    return_date DATE,
    FOREIGN KEY (book_id) REFERENCES books(id) ON DELETE CASCADE,
    FOREIGN KEY (reader_id) REFERENCES readers(id) ON DELETE CASCADE
);

INSERT INTO loans (book_id, reader_id, issue_date, return_date) VALUES
(1, 1, '2025-06-01', '2025-12-01'),
(2, 2, '2025-06-05', '2025-12-05');

ALTER TABLE loans ADD COLUMN actual_return_date DATE;
ALTER TABLE loans ADD COLUMN is_overdue BOOLEAN DEFAULT FALSE;

CREATE TABLE `users` (
`id` INT NOT NULL AUTO_INCREMENT,
`username` varchar(64) NOT NULL,
`password` varchar(64) NOT NULL,
`authority` varchar(64) NOT NULL,
PRIMARY KEY (`id`)
);

insert into `users` (id, username, password, authority) values
(1, 'admin', '$2a$10$jrryFNptnoGWwyWhxc47eeeHpin/LPOut7J221Xv4DB3qTswVcvJS','ROLE_ADMIN'),
(2, 'user', '$2a$10$I0BOCCDqRH6905RIlUmgd.2L008fmT3QvFtjEynyJQ2WoKDFRNGo6','ROLE_USER');