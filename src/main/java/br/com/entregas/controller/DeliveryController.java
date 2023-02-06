package br.com.entregas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import br.com.entregas.entity.Delivery;
import br.com.entregas.entity.Deliveryman;
import br.com.entregas.service.DeliveryManService;
import br.com.entregas.service.DeliveryService;

@Controller
@RequestMapping("/entrega")
public class DeliveryController {
	
	private static final String DELIVERY_VIEW = "CadastroEntrega";
	private static final String SEARCH_VIEW_DELIVERY = "PesquisaEntrega";

	@Autowired
	private DeliveryService service;
	
	@RequestMapping("/novo")
	public ModelAndView newDelivery() {
		ModelAndView mv = new ModelAndView(DELIVERY_VIEW);
		mv.addObject(new Delivery());
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView save(@Validated Delivery delivery, Errors errors, RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView(DELIVERY_VIEW);		
		
		if (errors.hasErrors()) {
			return mv;
		}
		try {
			service.save(delivery);
			mv.addObject("mensagem", "Entrega salva com sucesso!");
			return mv;

		} catch (IllegalArgumentException e) {
			errors.rejectValue("client", null, e.getMessage());
			mv.addObject("mensagem", "Selecionar o cliente");
			return mv;
		}
	}
	
	@RequestMapping("/pesquisar")
	public ModelAndView ListAll() {
		List<Delivery> listDelivery = service.listAllDelivery();
		
		ModelAndView mv = new ModelAndView(SEARCH_VIEW_DELIVERY);
		mv.addObject("deliveries", listDelivery);
		return mv;
	}
	
	@RequestMapping("{id}")
	public ModelAndView edit(@PathVariable("id") Delivery delivery) {
			
		ModelAndView mv = new ModelAndView(DELIVERY_VIEW);
		mv.addObject(delivery);
		return mv;
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable Long id, RedirectAttributes attributes) {

		service.delete(id);
		
		attributes.addFlashAttribute("mensagem", "Entrega excluída com sucesso!");
		return "redirect:/entrega/pesquisar";
	}
}

