package br.com.senac.crm.controllers;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import br.com.senac.crm.models.Acao;
import br.com.senac.crm.models.Produto;
import br.com.senac.crm.service.AcaoServiceImpl;
import br.com.senac.crm.service.StatusServiceImpl;
import br.com.senac.crm.util.DateUtil;
import javassist.tools.rmi.ObjectNotFoundException;

@Controller
public class AcaoController {

	@Autowired
	private DateUtil dateUtil;

	@Autowired
	private AcaoServiceImpl as;

	@Autowired
	private StatusServiceImpl ss;
	
	@GetMapping("/cadastraAcao")
	public ModelAndView formCadastraAcao() {
		System.out.println(dateUtil.formatLocaldateTimeToDatabaseStyle(LocalDateTime.now()));
		ModelAndView mv = new ModelAndView("acao/formAcaoModal");
		mv.addObject("status", ss.searchAll());
		mv.addObject("acao", new Acao());
		return mv;
	}

	@GetMapping("/acoes")
	public ModelAndView listaAcao() {
		System.out.println(dateUtil.formatLocaldateTimeToDatabaseStyle(LocalDateTime.now()));
		ModelAndView mv = new ModelAndView("acao/tabelaAcao");
		mv.addObject("acoes", as.searchAll());
		return mv;
	}

	@PostMapping("/salvaAcao")
	public ModelAndView salvaAcao(Acao acao, RedirectAttributes redirectAttributes) {
		as.save(acao);
		redirectAttributes.addFlashAttribute("msg_resultado", "Ação cadastrada com sucesso!");
		return listaAcao();
	}

	@GetMapping("/alteraAcao/{id}")
	public ModelAndView formAlteraAcao(@PathVariable("id") Integer id) throws ObjectNotFoundException {
		ModelAndView mv = new ModelAndView("acao/alteraAcaoModal");
		mv.addObject("acao", as.search(id));
		mv.addObject("status", ss.searchAll());
		return mv;
	}
	
	@PostMapping("/alteraAcao")
	public ModelAndView alteraProduto(Acao acao) throws ObjectNotFoundException {
		as.edit(acao);
		return listaAcao();
	}
}