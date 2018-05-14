package be.vdab.fietsacademy.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import be.vdab.fietsacademy.enums.Geslacht;

@Entity
@Table(name="docenten")
public class Docent implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private long id;
	private String voornaam;
	private String familienaam;
	private BigDecimal wedde;
	private String emailAdres;
	@Enumerated(EnumType.STRING)
	private Geslacht geslacht;

	public long getId() {
		return id;
	}
	public String getVoornaam() {
		return voornaam;
	}
	public String getFamilienaam() {
		return familienaam;
	}

	public BigDecimal getWedde() {
		return wedde;
	}
	
	public String getEmailAdres() {
		return emailAdres;
	}
	
	public Geslacht getGeslacht() {
		return geslacht;
	}
}