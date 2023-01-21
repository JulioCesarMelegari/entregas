package br.com.entregas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.entregas.entity.Adress;
import br.com.entregas.repository.AdressRepository;

@Service
public class AdressService {
	
	@Autowired
	private AdressRepository repository;
	
	public void save(Adress adress) {
		repository.save(adress);
	}

}
