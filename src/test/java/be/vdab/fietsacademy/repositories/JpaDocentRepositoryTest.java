package be.vdab.fietsacademy.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

import java.math.BigDecimal;

import org.junit.Before;
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
import be.vdab.fietsacademy.enums.Geslacht;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Sql("/insertDocent.sql")
@Import(JpaDocentRepository.class)
public class JpaDocentRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
	@Autowired
	private JpaDocentRepository repository;
	private static final String DOCENTEN= "docenten";
	private Docent docent;
	
	@Before
	public void before() {
		docent = new Docent("test","test",BigDecimal.TEN, "test@fietsacademy.be",Geslacht.MAN);
	}
	
	private long idVanTestMan() {
		return super.jdbcTemplate.queryForObject(
				"select id from docenten where voornaam = 'testM'",Long.class);
	}
	
	private long idVanTestVrouw() {
		return super.jdbcTemplate.queryForObject(
				"select id from docenten where voornaam = 'testV'",Long.class);
	}
	
	@Test
	public void create_voegt_een_docent_toe() {
		int aantalDocenten = super.countRowsInTable(DOCENTEN);
		repository.create(docent);
		assertEquals(aantalDocenten+1,super.countRowsInTable(DOCENTEN));
		assertNotEquals(0,docent.getId());
		assertEquals(1,super.countRowsInTableWhere(DOCENTEN,"id = "+docent.getId()));
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
	
	@Test
	public void read_leest_correct_geslacht_in() {
		assertEquals(Geslacht.MAN,repository.read(idVanTestMan()).get().getGeslacht());
		assertEquals(Geslacht.VROUW,repository.read(idVanTestVrouw()).get().getGeslacht());
	}

}