package br.com.entregas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.entregas.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{

}
