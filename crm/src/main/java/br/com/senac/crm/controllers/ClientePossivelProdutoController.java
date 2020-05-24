package br.com.senac.crm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import br.com.senac.crm.models.Cliente;
import br.com.senac.crm.repository.ClientePossivelProdutoRepository;

@Controller
public class ClientePossivelProdutoController {

	@Autowired
	private ClientePossivelProdutoRepository cpr;

	@GetMapping("/cadastraClientePossivelProduto")
	public String form() {
		return "clientePotencial/formClientePossivelProduto";
	}

	@GetMapping("/clientesPossiveis")
	public ModelAndView listarClientes() {
		ModelAndView mv = new ModelAndView("clientePotencial/listaClientes");
		Iterable<Cliente> clientes = cpr.findAll();
		mv.addObject("clientes", clientes);
		return mv;
	}
	
	@PostMapping("/cadastraClientePossivelProduto")
	public String form(Cliente clientePotencial) {
		cpr.save(clientePotencial);
		return "redirect:/cadastraCliente";
	}
}
