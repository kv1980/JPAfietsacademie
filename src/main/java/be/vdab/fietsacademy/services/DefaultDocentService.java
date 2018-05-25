package be.vdab.fietsacademy.services;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import be.vdab.fietsacademy.repositories.DocentRepository;

@Service
class DefaultDocentService implements DocentService {
	private final DocentRepository docentRepository;
	
	DefaultDocentService(DocentRepository docentRepository){
		this.docentRepository = docentRepository;
	}

	@Override
	public void opslag(long id, BigDecimal percentage) {
		throw new UnsupportedOperationException();
	}
}
