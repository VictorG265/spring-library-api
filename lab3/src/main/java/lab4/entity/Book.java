package lab4.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "books")
public class Book extends AbstractEntity {

	@NotBlank(message = "Название книги не может быть пустым")
	@Size(max = 255, message = "Название книги не должно превышать 255 символов")
	@Column(nullable = false)
	private String title;

	@NotBlank(message = "Имя автора не может быть пустым")
	@Size(max = 255, message = "Имя автора не должно превышать 255 символов")
	@Column(nullable = false)
	private String author;

	@Size(max = 100, message = "Жанр не должен превышать 100 символов")
	@Column
	private String genre;

	@Min(value = 1, message = "Количество должно быть минимум 1")
	@Column(nullable = false)
	private int quantity;

	// Геттеры и сеттеры
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
