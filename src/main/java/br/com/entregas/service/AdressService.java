package br.com.entregas.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.entregas.entity.Adress;
import br.com.entregas.entity.AdressFilter;
import br.com.entregas.repository.AdressRepository;

@Service
public class AdressService {
	
	@Autowired
	private AdressRepository repository;
	
	
	public void save(Adress adress) {
		
		try {
			
			repository.save(adress);
			
		} catch (DataIntegrityViolationException e){
			
			throw new IllegalArgumentException("Dados invalidos");
		}	
	}
	
	public void delete(Long id) {		
		repository.deleteById(id);
	}
	
	@SuppressWarnings("deprecation")
	public Adress getAdressById(Long id) {
		return repository.getById(id);
	}
	
	public List<Adress> filter (AdressFilter adressFilter){
		String street = adressFilter.getStreet() == null ? "%" : adressFilter.getStreet();
		return repository.findByStreetContaining(street);
	}
	
	public List<Adress> getAdressByClient (String idClient){
		return repository.findByidClientContaining(idClient);
	}


	public List<Adress> listAllAdress() {
		List<Adress> list = repository.findAllByOrderByStreetAsc();
		return list;
	}
	
	@Transactional
	public List<Adress> getAllAdressByClient(String idClient){
		List<Adress> allAdress = (List<Adress>) repository.findAllByIdClient(idClient);
		return allAdress;
	}
	
	@Transactional
	public void deleteAllByAdress(List<Adress> adresses) {
		repository.deleteAll(adresses);
	}

}
