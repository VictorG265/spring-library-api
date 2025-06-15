package lab4.service;

import java.util.List;
import lab4.entity.AbstractEntity;

public interface Service<T extends AbstractEntity> {

	T read(Long id);

	List<T> read();

	void save(T entity);

	void delete(Long id);

	void update (T entity);
}
