package be.vdab.fietsacademy.repositories;

import java.util.List;
import java.util.Optional;

import be.vdab.fietsacademy.entities.Docent;

public interface DocentRepository {
	void create(Docent docent);
	Optional<Docent> read(long id);
	void delete(long id);
	List<Docent> findAll();
}
