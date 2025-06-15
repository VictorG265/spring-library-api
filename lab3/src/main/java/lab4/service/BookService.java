package lab4.service;

import lab4.entity.Book;

public interface BookService extends Service<Book> {
	Book readByTitle(String title);
	Book update(Long id, Book entity);
}
