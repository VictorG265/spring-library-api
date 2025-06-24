package lab4.service;

import lab4.entity.Loan;
import lab4.entity.Reader;

import java.util.List;

public interface ReaderService extends Service<Reader> {
	List<Reader> readByName(String name);
	Reader readByEmail(String email);
	List<Loan> getOverdueLoans(Long readerId);
	Reader update(Long id, Reader entity);
	List <Reader> findByIdBetween(Long from, Long to);
}

