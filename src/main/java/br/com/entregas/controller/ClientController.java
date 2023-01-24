package br.com.entregas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.entregas.entity.Client;
import br.com.entregas.service.ClientService;

@Controller
@RequestMapping("cliente")
public class ClientController {

	@Autowired
	private ClientService service;
	
	@RequestMapping("/novo")
	public String newClient() {
		return "CadastroCliente";
	}
	
	@RequestMapping("/endereco")
	public String adress() {
		return "CadastroEndereco";
	}
	
	@RequestMapping("/pesquisa")
	public String pesquisa() {
		return "PesquisaCliente";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView save(Client client) {
		
		service.save(client);
		
		ModelAndView mv = new ModelAndView("CadastroCliente");
		mv.addObject("mensagem", "Cliente salvo com sucesso!!!!");
		return mv;
	}	
}

