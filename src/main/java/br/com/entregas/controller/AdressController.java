package br.com.entregas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView save(@Validated Adress adress, Errors errors) {
		ModelAndView mv = new ModelAndView(ADRESS_VIEW);
		
		if(errors.hasErrors()) {
			return mv;
		}
		
		//clientService.getById(null)
		adressService.save(adress);
		mv.addObject("mensagem", "Endereço salvo com sucesso!!!!");
		return mv;
	}
	
	/**
	 * tem que pegar o id cliente da view anterior, enviar o id para a view endereco, fazer busca no repositoriocliente com o id e setar cliente do endereço.
	 */

}
