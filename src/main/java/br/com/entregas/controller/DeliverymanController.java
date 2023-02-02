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
import br.com.entregas.entity.Deliveryman;
import br.com.entregas.service.DeliveryManService;

@Controller
@RequestMapping("/entregador")
public class DeliverymanController {
	
	private static final String DELIVERYMAN_VIEW = "CadastroEntregador";
	private static final String SEARCH_VIEW_DELIVERYMAN = "PesquisaEntregador";

	@Autowired
	private DeliveryManService service;
	
	@RequestMapping("/novo")
	public ModelAndView newDeliveryman() {
		ModelAndView mv = new ModelAndView(DELIVERYMAN_VIEW);
		mv.addObject(new Deliveryman());
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView save(@Validated Deliveryman deliveryman, Errors errors, RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView(DELIVERYMAN_VIEW);		
		
		if (errors.hasErrors()) {
			return mv;
		}
		try {
			service.save(deliveryman);
			mv.addObject("mensagem", "Entregador salvo com sucesso!");
			return mv;

		} catch (IllegalArgumentException e) {
			errors.rejectValue("name", null, e.getMessage());
			mv.addObject("mensagem", "Preencher nome");
			return mv;
		}
	}
	
	@RequestMapping("/pesquisar")
	public ModelAndView ListAll() {
		List<Deliveryman> listDeliveryman = service.listAllDeliveryman();
		
		ModelAndView mv = new ModelAndView(SEARCH_VIEW_DELIVERYMAN);
		mv.addObject("deliverymans", listDeliveryman);
		return mv;
	}
	
	@RequestMapping("{id}")
	public ModelAndView edit(@PathVariable("id") Deliveryman deliveryman) {
			
		ModelAndView mv = new ModelAndView(DELIVERYMAN_VIEW);
		mv.addObject(deliveryman);
		return mv;
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable Long id, RedirectAttributes attributes) {

		service.delete(id);
		
		attributes.addFlashAttribute("mensagem", "Entregador excluído com sucesso!");
		return "redirect:/entregador/pesquisar";
	}
	
	
}

