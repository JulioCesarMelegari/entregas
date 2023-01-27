package br.com.entregas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import br.com.entregas.entity.Client;
import br.com.entregas.entity.ClientFilter;
import br.com.entregas.service.ClientService;

@Controller
@RequestMapping("cliente")
public class ClientController {
	
	private static final String CLIENT_VIEW = "CadastroCliente";
	private static final String SEARCH_VIEW = "PesquisaCliente";

	@Autowired
	private ClientService service;
	
	@RequestMapping("/novo")
	public ModelAndView newClient() {
		ModelAndView mv = new ModelAndView(CLIENT_VIEW);
		mv.addObject(new Client());
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String save(@Validated Client client, Errors errors, RedirectAttributes attributes) {

		if (errors.hasErrors()) {
			return CLIENT_VIEW;
		}
		try {
			service.save(client);
			attributes.addFlashAttribute("mensagem", "Cliente salvo com sucesso! Cadastre o endereço de entrega!");
			return "redirect:/cliente/novo";

		} catch (IllegalArgumentException e) {
			errors.rejectValue("name", null, e.getMessage());
			return "redirect:/cliente/novo";
		}
	}
	
	@RequestMapping("/pesquisar")
	public ModelAndView ListAll() {
		List<Client> listClient = service.listAllClient();
		
		ModelAndView mv = new ModelAndView(SEARCH_VIEW);
		mv.addObject("clients", listClient);
		return mv;
	}
	
	@RequestMapping("{id}")
	public ModelAndView edit(@PathVariable("id") Client client) {
				
		ModelAndView mv = new ModelAndView(CLIENT_VIEW); 
		mv.addObject(client);
		return mv;
	}
	
	@RequestMapping(value="{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable Long id, RedirectAttributes attributes) {
		service.delete(id);
		
		attributes.addFlashAttribute("mensagem", "Cliente excluído com sucesso!");
		return "redirect:/cliente";
	}
	
}

