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

import br.com.entregas.entity.Adress;
import br.com.entregas.service.AdressService;

@Controller
@RequestMapping("/endereco")
public class AdressController {
	
	private static final String ADRESS_VIEW = "CadastroEndereco";
	
	@Autowired
	private AdressService adressService;
	
	
	@RequestMapping("/novo")
	public ModelAndView newAdress() {
		ModelAndView mv = new ModelAndView(ADRESS_VIEW);
		mv.addObject(new Adress());
		return mv;
	}
	
	@RequestMapping("/novo/{id}")
	public ModelAndView newAdressByClient(@PathVariable("id") String id) {
		
		Adress adress = new Adress();
		
		adress.setIdClient(id);
		
		ModelAndView mv = new ModelAndView(ADRESS_VIEW);
		mv.addObject(adress);
		return mv;
	}
	
	@RequestMapping( method = RequestMethod.POST)
	public ModelAndView save(@Validated Adress adress, Errors errors, RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView(ADRESS_VIEW);
		

		if(errors.hasErrors()) {
			return mv;
		}
		try {
			adressService.save(adress);
			mv.addObject("mensagem", "Endereço cadastrado com sucesso!");
			return mv;
		} catch (IllegalArgumentException e) {
			errors.rejectValue("street", null, e.getMessage());
			mv.addObject("mensagem", "Necessário inserir nome da rua");
			return mv;
		}
	}
	
	
	@RequestMapping("{id}")
	public ModelAndView edit(@PathVariable("id") Adress adress) {
		
		ModelAndView mv = new ModelAndView(ADRESS_VIEW);
		mv.addObject(adress);
		return mv;
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ModelAndView delete(@PathVariable Long id, RedirectAttributes attributes) {
		
		Adress oldAdress = adressService.getAdressById(id);
		
		String idClient = oldAdress.getIdClient();
		
		Adress newAdress = new Adress();
		
		newAdress.setIdClient(idClient);
		
		adressService.delete(id);
		
		ModelAndView mv = new ModelAndView(ADRESS_VIEW);
		attributes.addFlashAttribute("mensagem", "Endereco excluido com sucesso!");
		mv.addObject(newAdress);
		mv.addObject("mensagem");
		return mv;

	}
}
