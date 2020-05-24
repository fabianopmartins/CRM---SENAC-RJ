package br.com.senac.crm.controllers;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.senac.crm.models.Cliente;
import br.com.senac.crm.service.AcaoUsuarioClienteServiceImpl;
import br.com.senac.crm.service.ClienteServiceImpl;
import br.com.senac.crm.service.StatusServiceImpl;
import br.com.senac.crm.util.DateUtil;
import javassist.tools.rmi.ObjectNotFoundException;

@Controller
public class ClienteController {

	@Autowired
	private DateUtil dateUtil;

	@Autowired
	private ClienteServiceImpl cs;

	@Autowired
	private StatusServiceImpl ss;

	@Autowired
	private AcaoUsuarioClienteServiceImpl aucs;

	@GetMapping("/cadastraCliente")
	public ModelAndView formCadastraCliente() {
		ModelAndView mv = new ModelAndView("cliente/formCliente");
		mv.addObject("status", ss.searchAll());
		mv.addObject("cliente", new Cliente());
		return mv;
	}

	@GetMapping("/clientes")
	public ModelAndView listaCliente() {
		ModelAndView mv = new ModelAndView("cliente/tabelaCliente");
		mv.addObject("clientes", cs.searchAll());
		return mv;
	}

	@GetMapping("/timelineCliente/{id}")
	public ModelAndView timelineCliente(@PathVariable("id") Integer id) throws ObjectNotFoundException {
		System.out.println(dateUtil.formatLocaldateTimeToDatabaseStyle(LocalDateTime.now()));
		ModelAndView mv = new ModelAndView("cliente/timelineCliente");
		mv.addObject("acoes", aucs.searchAll());
		return mv;
	}

	@GetMapping("/acaoCliente/{id}")
	public ModelAndView acaoCliente(@PathVariable("id") Integer id) throws ObjectNotFoundException {
		System.out.println(dateUtil.formatLocaldateTimeToDatabaseStyle(LocalDateTime.now()));
		ModelAndView mv = new ModelAndView("cliente/acaoClienteModal");
		mv.addObject("cliente", cs.search(id));
		return mv;
	}

	@GetMapping("/alteraCliente/{id}")
	public ModelAndView alteraCliente(@PathVariable("id") Integer id) throws ObjectNotFoundException {
		System.out.println(dateUtil.formatLocaldateTimeToDatabaseStyle(LocalDateTime.now()));
		ModelAndView mv = new ModelAndView("cliente/alteraClienteModal");
		mv.addObject("cliente", cs.search(id));
		mv.addObject("status", ss.searchAll());
		return mv;
	}

	@PostMapping("/alteraCliente")
	public ModelAndView alteraCliente(Cliente cliente) throws ObjectNotFoundException {
		cs.edit(cliente);
		return listaCliente();
	}

	@PostMapping("/salvaCliente")
	public String salvaCliente(Cliente cliente, RedirectAttributes redirectAttributes) {
		cs.save(cliente);
		redirectAttributes.addFlashAttribute("msg_resultado", "Cliente cadastrado com sucesso!");
		return "redirect:/cadastraCliente";
	}

	@GetMapping("/visualizaCliente/{id}")
	public ModelAndView visualizaCliente(@PathVariable("id") Integer id) throws ObjectNotFoundException {
		ModelAndView mv = new ModelAndView("cliente/visualizaClienteModal");
		mv.addObject("cliente", cs.search(id));
		return mv;
	}
}