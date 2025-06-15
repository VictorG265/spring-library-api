package lab4.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "readers")
public class Reader extends AbstractEntity {

	@NotBlank(message = "Имя читателя не может быть пустым")
	@Size(max = 255, message = "Имя читателя не должно превышать 255 символов")
	@Column(nullable = false)
	private String name;

	@NotBlank(message = "Email обязателен")
	@Email(message = "Некорректный формат email")
	@Column(unique = true, nullable = false)
	private String email;

	// Геттеры и сеттеры
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
