package br.com.entregas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.entregas.entity.Deliveryman;

public interface DeliveryManRepository extends JpaRepository<Deliveryman, Long>{

	public List<Deliveryman> findByNameContaining(String name);
	
	public List<Deliveryman> findAllByOrderByNameAsc();
	
}
