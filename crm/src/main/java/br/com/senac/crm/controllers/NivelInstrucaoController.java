package br.com.senac.crm.controllers;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.senac.crm.models.NivelInstrucao;
import br.com.senac.crm.service.NivelInstrucaoServiceImpl;
import br.com.senac.crm.service.StatusServiceImpl;
import br.com.senac.crm.util.DateUtil;
import javassist.tools.rmi.ObjectNotFoundException;

@Controller
public class NivelInstrucaoController {

	@Autowired
	private DateUtil dateUtil;

	@Autowired
	private NivelInstrucaoServiceImpl nis;

	@Autowired
	ProdutoController produto;

	@Autowired
	private StatusServiceImpl ss;

	@GetMapping("/cadastraNivel")
	public ModelAndView formCadastraNivelInstrucao() {
		System.out.println(dateUtil.formatLocaldateTimeToDatabaseStyle(LocalDateTime.now()));
		ModelAndView mv = new ModelAndView("nivelInstrucao/formNivelModal");
		mv.addObject("status", ss.searchAll());
		mv.addObject("nivel", new NivelInstrucao());
		return mv;
	}

	@GetMapping("/niveis")
	public ModelAndView listaNivelInstrucao() {
		System.out.println(dateUtil.formatLocaldateTimeToDatabaseStyle(LocalDateTime.now()));
		ModelAndView mv = new ModelAndView("nivelInstrucao/tabelaNivelInstrucao");
		mv.addObject("niveis", nis.searchAll());
		return mv;
	}

	@GetMapping("/alteraNivel/{id}")
	public ModelAndView formAlteraNivelInstrucao(@PathVariable("id") Integer id) throws ObjectNotFoundException {
		ModelAndView mv = new ModelAndView("nivelInstrucao/alteraNivelModal");
		mv.addObject("status", ss.searchAll());
		mv.addObject("nivel", nis.search(id));
		return mv;
	}

	@PostMapping("/alteraNivel")
	public ModelAndView alteraNivelInstrucao(NivelInstrucao nivelInstrucao) throws ObjectNotFoundException {
		nis.edit(nivelInstrucao);
		return listaNivelInstrucao();
	}

	@PostMapping("/salvaNivel")
	public ModelAndView salvaNivelInstrucao(NivelInstrucao nivelInstrucao, RedirectAttributes redirectAttributes) {
		nis.save(nivelInstrucao);
		redirectAttributes.addFlashAttribute("msg_resultado", "Nível de Instrução cadastrado com sucesso!");
		return produto.formCadastraProduto();
	}
}