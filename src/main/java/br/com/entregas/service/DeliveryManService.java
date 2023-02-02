package br.com.entregas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import br.com.entregas.entity.Deliveryman;
import br.com.entregas.repository.DeliveryManRepository;

@Service
public class DeliveryManService {
	
	@Autowired
	private DeliveryManRepository repository;
	
	public void save(Deliveryman deliveryman) {
		try {
			repository.save(deliveryman);
		}catch(DataIntegrityViolationException e){
			throw new IllegalArgumentException("Dados invalidos");
		}
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	@SuppressWarnings("deprecation")
	public Deliveryman getClientByID(Long id) {
		return repository.getById(id);
	}
	
	public List<Deliveryman> filter(Deliveryman deliverymanFilter){
		String name = deliverymanFilter.getName() == null ? "%" : deliverymanFilter.getName();
		return repository.findByNameContaining(name);
	}
	
	public List<Deliveryman> listAllDeliveryman(){
		List<Deliveryman> list = repository.findAllByOrderByNameAsc();
		return  list;
	}
}
