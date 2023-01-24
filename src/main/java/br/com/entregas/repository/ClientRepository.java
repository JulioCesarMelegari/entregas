package br.com.entregas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.entregas.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{

	public List<Client> findByNameContaining(String name);
	
	public List<Client> findAllByOrderByNameAsc();
	
}
