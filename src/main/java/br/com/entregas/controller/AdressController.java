package br.com.entregas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.entregas.entity.Adress;
import br.com.entregas.service.AdressService;

@Controller
@RequestMapping("ControleEntregas/endereco")
public class AdressController {
	
	@Autowired
	private AdressService adressService;
	
	@RequestMapping("/novo")
	public String newAdress() {
		return "CadastroEndereco";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView save(Adress adress) {
		
		//clientService.getById(null)
		
		adressService.save(adress);
		
		ModelAndView mv = new ModelAndView("CadastroEndereco");
		mv.addObject("mensagem", "Endereço salvo com sucesso!!!!");
		mv.addObject("mensagem", "Endereço salvo com sucesso!!!!");
		return mv;
	}
	
	/**
	 * tem que pegar o id cliente da view anterior, enviar o id para a view endereco, fazer busca no repositoriocliente com o id e setar cliente do endereço.
	 */

}
