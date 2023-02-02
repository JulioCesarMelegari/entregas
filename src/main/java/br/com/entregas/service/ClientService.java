package br.com.entregas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.entregas.entity.Client;
import br.com.entregas.entity.ClientFilter;
import br.com.entregas.repository.ClientRepository;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository clientrepository;
	
	public void save(Client client) {
		try {
			clientrepository.save(client);
		}catch(DataIntegrityViolationException e){
			throw new IllegalArgumentException("Dados invalidos");
		}
	}
	
	public void delete(Long id) {
		clientrepository.deleteById(id);
	}
	
	@SuppressWarnings("deprecation")
	public Client getClientByID(Long id) {
		return clientrepository.getById(id);
	}
	
	public List<Client> filter(ClientFilter clientFilter){
		String name = clientFilter.getName() == null ? "%" : clientFilter.getName();
		return clientrepository.findByNameContaining(name);
	}
	
	public List<Client> listAllClient(){
		List<Client> list = clientrepository.findAllByOrderByNameAsc();
		return  list;
	}
}
