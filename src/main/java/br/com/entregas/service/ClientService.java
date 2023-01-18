package br.com.entregas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.entregas.entity.Client;
import br.com.entregas.repository.ClientRepository;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository clientrepository;
	
	public void save(Client client) {
		clientrepository.save(client);
	}
}
