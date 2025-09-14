package marketplace.core;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public abstract class BaseService<C, R extends JpaRepository<C, Long>> {
	protected final R repostiory;

	
	protected BaseService(R repository) {
		this.repostiory = repository;
	}
	
	public List<C> getAll() {
		return this.repostiory.findAll();
	}
	
	public Optional<C> getById(Long id) {
		return this.repostiory.findById(id);
	}
	
	public C createOne(C product) {
		return this.repostiory.save(product);
	}
	
	public void deleteById(Long id) {
		if (this.repostiory.existsById(id)) {
			this.repostiory.deleteById(id);
		} else {
			throw new RuntimeException("Item is not found by id" + id);
		}
	}

}
