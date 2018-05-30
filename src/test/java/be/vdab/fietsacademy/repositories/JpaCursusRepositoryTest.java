package be.vdab.fietsacademy.repositories;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.Optional;

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

import be.vdab.fietsacademy.entities.Cursus;
import be.vdab.fietsacademy.entities.GroepsCursus;
import be.vdab.fietsacademy.entities.IndividueleCursus;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Sql("/insertCursus.sql")
@Import(JpaCursusRepository.class)
public class JpaCursusRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
	@Autowired
	private CursusRepository repository;
	private static final String CURSUSSEN = "cursussen";   
	
	private long idVanTestGroep() {
		return super.jdbcTemplate.queryForObject("select id from cursussen where naam='testGroep'",Long.class);
	}
	
	private long idVanTestIndividu() {
		return super.jdbcTemplate.queryForObject("select id from cursussen where naam='testIndividu'",Long.class);
	}
	
	@Test
	public void read_leest__bestaande_groepscursus_in() {
		Optional<Cursus> optioneleCursus = repository.read(idVanTestGroep());
		assertEquals("testGroep", ((GroepsCursus)optioneleCursus.get()).getNaam());
	}
	
	@Test
	public void read_leest__bestaande_individuele_cursus_in() {
		Optional<Cursus> optioneleCursus = repository.read(idVanTestIndividu());
		assertEquals("testIndividu",((IndividueleCursus) optioneleCursus.get()).getNaam());
	}
	
	@Test   
	public void createGroepsCursus() {     
		int aantalGroepsCursussen = super.countRowsInTableWhere(CURSUSSEN,"soort='G'");     
		GroepsCursus cursus = new GroepsCursus("testGroep2", LocalDate.of(2018,01,01), LocalDate.of(2018, 12, 31));     
		repository.create(cursus);     
		assertEquals(aantalGroepsCursussen + 1, super.countRowsInTableWhere(CURSUSSEN, "soort='G'"));
		assertEquals(1,super.countRowsInTableWhere(CURSUSSEN, "id=" + cursus.getId()));   
	}   
	
	@Test   
	public void createIndividueleCursus() {     
		int aantalIndividueleCursussen = super.countRowsInTableWhere(CURSUSSEN,"soort='I'");     
		IndividueleCursus cursus = new IndividueleCursus("testIndividueel2", 7);     
		repository.create(cursus);     
		assertEquals(aantalIndividueleCursussen + 1, super.countRowsInTableWhere(CURSUSSEN, "soort='I'"));     
		assertEquals(1,super.countRowsInTableWhere(CURSUSSEN, "id=" + cursus.getId()));   
	} 
}
