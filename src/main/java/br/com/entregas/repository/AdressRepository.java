package br.com.entregas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.entregas.entity.Adress;

public interface AdressRepository extends JpaRepository<Adress,Long> {

}