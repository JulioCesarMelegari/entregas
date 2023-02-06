package br.com.entregas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.entregas.entity.Delivery;

public interface DeliveryRepository extends JpaRepository<Delivery, Long>{

	
}
