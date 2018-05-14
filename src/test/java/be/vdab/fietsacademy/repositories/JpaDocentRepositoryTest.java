package be.vdab.fietsacademy.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import be.vdab.fietsacademy.entities.Docent;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Sql("/insertDocent.sql")
@Import(JpaDocentRepository.class)
public class JpaDocentRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
	@Autowired
	private JpaDocentRepository repository;
	
	private long idVanTestMan() {
		return super.jdbcTemplate.queryForObject(
				"select id from docenten where voornaam = 'testM'",Long.class);
	}
	
	@Test
	public void read_leest_bestaande_docent() {
		Docent docent = repository.read(idVanTestMan()).get();
		assertEquals("testM",docent.getVoornaam());
	}
	
	@Test
	public void read_leest_onbestaande_docent_niet() {
		assertFalse(repository.read(-1L).isPresent());
	}

}
