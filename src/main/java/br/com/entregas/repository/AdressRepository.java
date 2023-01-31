package br.com.entregas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.entregas.entity.Adress;

public interface AdressRepository extends JpaRepository<Adress,Long> {

	public List<Adress> findByStreetContaining(String street);

	public List<Adress> findAllByOrderByStreetAsc();

	public List<Adress> findByidClientContaining(String idAdress);

	public List<Adress> findAllByIdClient(String idClient);
}
