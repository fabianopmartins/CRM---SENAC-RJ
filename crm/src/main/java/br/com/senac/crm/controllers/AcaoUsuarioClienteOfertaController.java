package br.com.senac.crm.controllers;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.senac.crm.models.AcaoUsuarioClienteOferta;
import br.com.senac.crm.service.AcaoServiceImpl;
import br.com.senac.crm.service.AcaoUsuarioClienteOfertaServiceImpl;
import br.com.senac.crm.service.AcaoUsuarioClienteServiceImpl;
import br.com.senac.crm.service.ClienteOfertaServiceImpl;
import br.com.senac.crm.service.ClienteServiceImpl;
import br.com.senac.crm.service.UsuarioServiceImpl;
import br.com.senac.crm.util.DateUtil;
import javassist.tools.rmi.ObjectNotFoundException;

@Controller
public class AcaoUsuarioClienteOfertaController {

	@Autowired
	private DateUtil dateUtil;

	@Autowired
	private AcaoUsuarioClienteOfertaServiceImpl aucs;

	@Autowired
	private AcaoUsuarioClienteServiceImpl aucos;

	@Autowired
	private ClienteOfertaServiceImpl cos;

	@Autowired
	private ClienteServiceImpl cs;
	
	@Autowired
	private ClienteController cc;
	
	@Autowired
	private AcaoServiceImpl as;

	@Autowired
	private UsuarioServiceImpl us;

	@GetMapping("/cadastraAcaoUsuarioClienteOferta/{id}")
	public ModelAndView formCadastraAcaoUsuarioClienteOferta(@PathVariable("id") Integer id)
			throws ObjectNotFoundException {
		System.out.println(dateUtil.formatLocaldateTimeToDatabaseStyle(LocalDateTime.now()));
		ModelAndView mv = new ModelAndView("acaoUsuarioClienteOferta/formAcaoUsuarioClienteOfertaModal");
		mv.addObject("cliente", cs.search(id));
		mv.addObject("clienteOfertas", cos.searchAll());
		mv.addObject("acoes", as.searchAll());
		mv.addObject("usuarios", us.searchAll());
		mv.addObject("acaoUsuarioClienteOferta", new AcaoUsuarioClienteOferta());
		return mv;
	}

	@GetMapping("/acaoUsuarioClienteOfertas")
	public ModelAndView listaAcoesUsuarioClienteOferta() {
		System.out.println(dateUtil.formatLocaldateTimeToDatabaseStyle(LocalDateTime.now()));
		ModelAndView mv = new ModelAndView("cliente/timelineCliente");
		mv.addObject("acaoUsuarioClienteOfertas", aucos.searchAll());
		mv.addObject("acaoUsuarioClientes", aucs.searchAll());
		return mv;
	}
	
	@PostMapping("/salvaAcaoUsuarioClienteOferta")
	public ModelAndView salvaAcaoUsuarioClienteOferta(AcaoUsuarioClienteOferta acaoUsuarioClienteOferta) throws ObjectNotFoundException {
		aucs.save(acaoUsuarioClienteOferta);
		return cc.listaCliente();
	}

//	@RequestMapping(value = "/visualizaTimelineClienteCliente/{id}", method = RequestMethod.GET)
//	public ModelAndView visualizaCliente(@PathVariable("id") Integer id) throws ObjectNotFoundException {
//		ModelAndView mv = new ModelAndView("cliente/visualizaClienteModal");
//		mv.addObject("cliente", cs.search(id));
//		return mv;
//	}

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

}