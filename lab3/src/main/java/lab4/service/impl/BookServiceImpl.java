package lab4.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lab4.entity.Book;
import lab4.repository.BookRepository;
import lab4.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository repository;

	@Override
	public Book read(Long id) {
		return repository.findById(id).orElseThrow(IllegalArgumentException::new);
	}

	@Override
	public List<Book> read() {
		return repository.findAll();
	}

	@Override
	public void save(Book entity) {
		repository.save(entity);
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
	}

	@Override public void update(Book entity) {
		Book book = repository.findById(entity.getId())
				.orElseThrow(IllegalArgumentException::new);
		book.setTitle(entity.getTitle());
		book.setAuthor(entity.getAuthor());
		book.setGenre(entity.getGenre());
		book.setQuantity(entity.getQuantity());
		repository.save(book); }

	@Override
	public Book readByTitle(String title) {
		return repository.findByTitle(title);
	}

	@Override
	public Book update(Long id, Book entity) {
		Book book = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Книга не найдена"));
		book.setTitle(entity.getTitle());
		book.setAuthor(entity.getAuthor());
		book.setGenre(entity.getGenre());
		book.setQuantity(entity.getQuantity());
		return repository.save(book);
	}
}
