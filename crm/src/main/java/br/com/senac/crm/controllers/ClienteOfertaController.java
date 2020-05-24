package br.com.senac.crm.controllers;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.senac.crm.models.ClienteOferta;
import br.com.senac.crm.service.ClienteOfertaServiceImpl;
import br.com.senac.crm.service.ClienteServiceImpl;
import br.com.senac.crm.service.OfertaServiceImpl;
import br.com.senac.crm.service.StatusServiceImpl;
import br.com.senac.crm.util.DateUtil;
import javassist.tools.rmi.ObjectNotFoundException;

@Controller
public class ClienteOfertaController {

	@Autowired
	private DateUtil dateUtil;

	@Autowired
	private ClienteOfertaServiceImpl cos;

	@Autowired
	private ClienteServiceImpl cs;

	@Autowired
	private OfertaServiceImpl os;

	@Autowired
	private StatusServiceImpl ss;

	@GetMapping("/cadastraClienteOferta")
	public ModelAndView formCadastraClienteOferta() {
		ModelAndView mv = new ModelAndView("clienteOferta/formClienteOferta");
		mv.addObject("status", ss.searchAll());
		mv.addObject("clientes", cs.searchAll());
		mv.addObject("ofertas", os.searchAll());
		mv.addObject("clienteOferta", new ClienteOferta());
		return mv;
	}

	@GetMapping("/clientesOfertas")
	public ModelAndView listaClienteOferta() {
		ModelAndView mv = new ModelAndView("clienteOferta/tabelaClienteOferta");
		mv.addObject("clientesOfertas", cos.searchAll());
		return mv;
	}

	@GetMapping("/alteraClienteOferta/{id}")
	public ModelAndView alteraClienteOferta(@PathVariable("id") Integer id) throws ObjectNotFoundException {
		System.out.println(dateUtil.formatLocaldateTimeToDatabaseStyle(LocalDateTime.now()));
		ModelAndView mv = new ModelAndView("clienteOferta/alteraClienteOfertaModal");
		mv.addObject("clienteOferta", cos.search(id));
		mv.addObject("clientes", cs.searchAll());
		mv.addObject("ofertas", os.searchAll());
		mv.addObject("status", ss.searchAll());
		return mv;
	}

	@PostMapping("/alteraClienteOferta")
	public ModelAndView alteraClienteOferta(ClienteOferta clienteOferta) throws ObjectNotFoundException {
		cos.edit(clienteOferta);
		return listaClienteOferta();
	}

	@PostMapping("/salvaClienteOferta")
	public String salvaClienteOferta(ClienteOferta clienteOferta, RedirectAttributes redirectAttributes) {
		cos.save(clienteOferta);
		redirectAttributes.addFlashAttribute("msg_resultado", "Cliente Oferta cadastrado com sucesso!");
		return "redirect:/cadastraClienteOferta";
	}

	@GetMapping("/visualizaClienteOferta/{id}")
	public ModelAndView visualizaClienteOferta(@PathVariable("id") Integer id) throws ObjectNotFoundException {
		ModelAndView mv = new ModelAndView("clienteOferta/visualizaClienteOfertaModal");
		mv.addObject("clienteOferta", cos.search(id));		
		return mv;
	}
}