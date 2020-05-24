package br.com.senac.crm.controllers;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.senac.crm.models.Produto;
import br.com.senac.crm.service.NivelInstrucaoServiceImpl;
import br.com.senac.crm.service.ProdutoServiceImpl;
import br.com.senac.crm.service.StatusServiceImpl;
import br.com.senac.crm.util.DateUtil;
import javassist.tools.rmi.ObjectNotFoundException;

@Controller
public class ProdutoController {

	@Autowired
	private DateUtil dateUtil;

	@Autowired
	private ProdutoServiceImpl ps;

	@Autowired
	private NivelInstrucaoServiceImpl nis;

	@Autowired
	private StatusServiceImpl ss;

	@GetMapping("/cadastraProduto")
	public ModelAndView formCadastraProduto() {
		System.out.println(dateUtil.formatLocaldateTimeToDatabaseStyle(LocalDateTime.now()));
		ModelAndView mv = new ModelAndView("produto/formProduto");
		mv.addObject("niveis", nis.searchAll());
		mv.addObject("status", ss.searchAll());
		mv.addObject("produto", new Produto());
		return mv;
	}

	@GetMapping("/produtos")
	public ModelAndView listaProduto() {
		System.out.println(dateUtil.formatLocaldateTimeToDatabaseStyle(LocalDateTime.now()));
		ModelAndView mv = new ModelAndView("produto/tabelaProduto");
		mv.addObject("produtos", ps.searchAll());
		return mv;
	}

	@RequestMapping(value = "/procuraProduto", method = RequestMethod.GET)
	@ResponseBody
	public Produto procuraProduto(Integer id) throws ObjectNotFoundException {
		System.out.println(dateUtil.formatLocaldateTimeToDatabaseStyle(LocalDateTime.now()));
		return ps.search(id);
	}

	@GetMapping("/alteraProduto/{id}")
	public ModelAndView alteraProduto(@PathVariable("id") Integer id) throws ObjectNotFoundException {
		System.out.println(dateUtil.formatLocaldateTimeToDatabaseStyle(LocalDateTime.now()));
		ModelAndView mv = new ModelAndView("produto/alteraProdutoModal");
		mv.addObject("produto", ps.search(id));
		mv.addObject("niveis", nis.searchAll());
		mv.addObject("status", ss.searchAll());
		return mv;
	}

	@PostMapping("/alteraProduto")
	public ModelAndView alteraProduto(Produto produto) throws ObjectNotFoundException {
		ps.edit(produto);
		return listaProduto();
	}

	@PostMapping("/salvaProduto")
	public String salvaProduto(Produto produto, RedirectAttributes redirectAttributes) {
		ps.save(produto);
		redirectAttributes.addFlashAttribute("msg_resultado", "Produto cadastrado com sucesso!");
		return "redirect:/cadastraProduto";
	}

	@GetMapping("/visualizaProduto/{id}")
	public ModelAndView deletaProduto(@PathVariable("id") Integer id) throws ObjectNotFoundException {
		System.out.println(dateUtil.formatLocaldateTimeToDatabaseStyle(LocalDateTime.now()));
		ModelAndView mv = new ModelAndView("produto/visualizaProdutoModal");
		mv.addObject("produto", ps.search(id));
		return mv;
	}
}