package br.com.senac.crm.controllers;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.senac.crm.models.Acao;
import br.com.senac.crm.models.CategoriaDado;
import br.com.senac.crm.models.NivelInstrucao;
import br.com.senac.crm.models.PerfilUsuario;
import br.com.senac.crm.models.Status;
import br.com.senac.crm.models.Usuario;
import br.com.senac.crm.service.ClienteOfertaServiceImpl;
import br.com.senac.crm.util.DateUtil;

@Controller
public class StartController {

	@Autowired
	private DateUtil dateUtil;

	@Autowired
	ClienteOfertaServiceImpl cos;

	@GetMapping("/inicio")
	public String home() {
		return "/index";
	}

	@GetMapping("/kanban")
	public ModelAndView kanabn() {
		ModelAndView mv = new ModelAndView("cliente/kanban");
		mv.addObject("clienteOfertas", cos.searchAll());
		return mv;
	}

	@GetMapping("/funcionalidades")
	public ModelAndView formFuncionalidadess() {
		System.out.println(dateUtil.formatLocaldateTimeToDatabaseStyle(LocalDateTime.now()));
		ModelAndView mv = new ModelAndView("/funcionalidades");
		mv.addObject("nivel", new NivelInstrucao());
		mv.addObject("acao", new Acao());
		mv.addObject("status", new Status());
		mv.addObject("usuario", new Usuario());
		mv.addObject("perfilUsuario", new PerfilUsuario());
		mv.addObject("categoriaDado", new CategoriaDado());
		return mv;
	}
}