package br.com.senac.crm.controllers;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import br.com.senac.crm.models.AcaoUsuarioCliente;
import br.com.senac.crm.service.AcaoServiceImpl;
import br.com.senac.crm.service.AcaoUsuarioClienteOfertaServiceImpl;
import br.com.senac.crm.service.AcaoUsuarioClienteServiceImpl;
import br.com.senac.crm.service.ClienteOfertaServiceImpl;
import br.com.senac.crm.service.ClienteServiceImpl;
import br.com.senac.crm.service.UsuarioServiceImpl;
import br.com.senac.crm.util.DateUtil;
import javassist.tools.rmi.ObjectNotFoundException;

@Controller
public class AcaoUsuarioClienteController {

	@Autowired
	private DateUtil dateUtil;

	@Autowired
	private AcaoUsuarioClienteServiceImpl aucs;

	@Autowired
	private AcaoUsuarioClienteOfertaServiceImpl aucos;

	@Autowired
	private ClienteServiceImpl cs;

	@Autowired
	private ClienteController cc;

	@Autowired
	private ClienteOfertaServiceImpl cos;

	@Autowired
	private AcaoServiceImpl as;

	@Autowired
	private UsuarioServiceImpl us;

	@GetMapping("/cadastraAcaoUsuarioCliente/{id}")
	public ModelAndView formCadastraAcaoUsuarioCliente(@PathVariable("id") Integer id) throws ObjectNotFoundException {
		System.out.println(dateUtil.formatLocaldateTimeToDatabaseStyle(LocalDateTime.now()));
		ModelAndView mv = new ModelAndView("acaoUsuarioCliente/formAcaoUsuarioClienteModal");
		mv.addObject("cliente", cs.search(id));
		mv.addObject("acoes", as.searchAll());
		mv.addObject("usuarios", us.searchAll());
		mv.addObject("acaoUsuarioCliente", new AcaoUsuarioCliente());
		return mv;
	}

	@GetMapping("/acaoUsuarioClientes/{id}")
	public ModelAndView listaAcaoUsuarioClienteInsert(@PathVariable("id") Integer id) throws ObjectNotFoundException {
		System.out.println(dateUtil.formatLocaldateTimeToDatabaseStyle(LocalDateTime.now()));
		ModelAndView mv = new ModelAndView("cliente/timelineCliente");
		mv.addObject("acaoUsuarioClientes", aucs.searchAll());
		mv.addObject("acaoUsuarioClienteOfertas", aucos.searchAll());
		mv.addObject("clienteOfertas", cos.searchAll());
		mv.addObject("cliente", cs.search(id));
		return mv;
	}

	@GetMapping("/acaoUsuarioClientes/")
	public ModelAndView listaAcaoUsuarioCliente() throws ObjectNotFoundException {
		System.out.println(dateUtil.formatLocaldateTimeToDatabaseStyle(LocalDateTime.now()));
		ModelAndView mv = new ModelAndView("cliente/timelineCliente");
		mv.addObject("acaoUsuarioCliente", aucs.searchAll());
		mv.addObject("acaoUsuarioClienteOferta", aucos.searchAll());
		return mv;
	}

	@PostMapping("/salvaAcaoUsuarioCliente")
	public ModelAndView salvaAcaoUsuarioCliente(AcaoUsuarioCliente acaoUsuarioCliente) throws ObjectNotFoundException {
		aucs.save(acaoUsuarioCliente);
		return cc.listaCliente();
	}

	@GetMapping("/visualizaTimelineCliente/{id}")
	public ModelAndView visualizaCliente(@PathVariable("id") Integer id) throws ObjectNotFoundException {
		ModelAndView mv = new ModelAndView("cliente/visualizaTimelineClienteModal");
		mv.addObject("acaoUsuarioClientes", aucs.searchAll());
		mv.addObject("cliente", cs.search(id));
		return mv;
	}

//	@RequestMapping(value = "/alteraNivel/{id}", method = RequestMethod.GET)
//	public ModelAndView formAlteraNivelInstrucao(@PathVariable("id") Integer id) throws ObjectNotFoundException {
//		ModelAndView mv = new ModelAndView("nivelInstrucao/alteraNivelModal");
//		mv.addObject("nivel", nis.search(id));
//		return mv;
//	}
//
//	@RequestMapping(value = "/alteraNivel", method = RequestMethod.POST)
//	public ModelAndView alteraNivelInstrucao(NivelInstrucao nivelInstrucao) throws ObjectNotFoundException {
//		nis.edit(nivelInstrucao);
//		return listaNivelInstrucao();
//	}
//
}