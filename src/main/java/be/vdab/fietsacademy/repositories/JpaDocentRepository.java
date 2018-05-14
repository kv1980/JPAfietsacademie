package be.vdab.fietsacademy.repositories;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import be.vdab.fietsacademy.entities.Docent;

@Repository
class JpaDocentRepository implements DocentRepository {
	private final EntityManager manager;
	
	public JpaDocentRepository(EntityManager manager) {
		this.manager = manager;
	}

	@Override
	public void create(Docent docent) {
		manager.persist(docent);		
	}
	
	@Override
	public Optional<Docent> read(long id) {
		return Optional.ofNullable(manager.find(Docent.class, id));
	}
}