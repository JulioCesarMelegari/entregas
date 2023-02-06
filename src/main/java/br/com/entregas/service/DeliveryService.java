package br.com.entregas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.entregas.entity.Delivery;
import br.com.entregas.entity.DeliveryFilter;
import br.com.entregas.entity.Deliveryman;
import br.com.entregas.repository.DeliveryManRepository;
import br.com.entregas.repository.DeliveryRepository;

@Service
public class DeliveryService {
	
	@Autowired
	private DeliveryRepository repository;
	
	public void save(Delivery delivery) {
		try {
			repository.save(delivery);
		}catch(DataIntegrityViolationException e){
			throw new IllegalArgumentException("Dados invalidos");
		}
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	@SuppressWarnings("deprecation")
	public Delivery getClientByID(Long id) {
		return repository.getById(id);
	}
	
	/*public List<Delivery> filter(DeliveryFilter deliveryFilter){
		String client = deliveryFilter.getClient() == null ? "%" : deliveryFilter.getClient();
		return repository.findByClientContaining(client);
	}*/
	
	public List<Delivery> listAllDelivery(){
		List<Delivery> list = repository.findAll();
		return  list;
	}
}
