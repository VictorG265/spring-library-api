package lab4.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import lab4.entity.Reader;
import org.springframework.stereotype.Repository;

@Repository
public interface ReaderRepository extends JpaRepository<Reader, Long> {
	List<Reader> findByName(String name);
	Reader findByEmail(String email);
}
