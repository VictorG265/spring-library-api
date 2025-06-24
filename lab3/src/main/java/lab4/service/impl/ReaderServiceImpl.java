package lab4.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import lab4.entity.Loan;
import lab4.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lab4.entity.Reader;
import lab4.repository.ReaderRepository;
import lab4.service.ReaderService;

@Service
public class ReaderServiceImpl implements ReaderService {

	@Autowired
	private ReaderRepository repository;

	@Autowired
	private LoanRepository loanRepository;

	@Override
	public Reader read(Long id) {
		return repository.findById(id).orElseThrow(IllegalArgumentException::new);
	}

	@Override
	public List<Reader> read() {
		return repository.findAll();
	}

	@Override
	public void save(Reader entity) {
		repository.save(entity);
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
	}

	@Override
	public List<Reader> readByName(String name) {
		return repository.findByName(name);
	}
	@Override
	public Reader readByEmail(String email) {
		return repository.findByEmail(email);
	}

	@Override
	public List<Loan> getOverdueLoans(Long readerId) {
		return loanRepository.findByReaderId(readerId).stream()
				.filter(loan -> {
					LocalDate today = LocalDate.now();
					return (loan.getReturnDate() != null && loan.getActualReturnDate() != null && loan.getActualReturnDate().isAfter(loan.getReturnDate()))
							|| (loan.getReturnDate() != null && loan.getActualReturnDate() == null && today.isAfter(loan.getReturnDate()));
				})
				.collect(Collectors.toList());
	}

	@Override
	public void update(Reader entity) {
		Reader reader = repository.findById(entity.getId()).orElseThrow(IllegalArgumentException::new);
		reader.setName(entity.getName());
		reader.setEmail(entity.getEmail());
		repository.save(reader);
	}

	@Override
	public Reader update(Long id, Reader entity) {
		Reader reader = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Читатель не найден"));
		reader.setName(entity.getName());
		reader.setEmail(entity.getEmail());
		return repository.save(reader);
	}

	@Override
	public List <Reader> findByIdBetween(Long from, Long to){
		return repository.findByIdBetween(from, to);
	}
}
