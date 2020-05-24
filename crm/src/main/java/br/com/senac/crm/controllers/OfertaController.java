package br.com.senac.crm.controllers;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.senac.crm.models.Oferta;
import br.com.senac.crm.service.OfertaServiceImpl;
import br.com.senac.crm.service.ProdutoServiceImpl;
import br.com.senac.crm.service.StatusServiceImpl;
import br.com.senac.crm.util.DateUtil;
import javassist.tools.rmi.ObjectNotFoundException;

@Controller
public class OfertaController {

	@Autowired
	private DateUtil dateUtil;

	@Autowired
	private ProdutoServiceImpl ps;

	@Autowired
	private OfertaServiceImpl os;

	@Autowired
	private StatusServiceImpl ss;

	@GetMapping("/cadastraOferta")
	public ModelAndView formCadastraOferta() {
		System.out.println(dateUtil.formatLocaldateTimeToDatabaseStyle(LocalDateTime.now()));
		ModelAndView mv = new ModelAndView("oferta/formOferta");
		mv.addObject("produtos", ps.searchAll());
		mv.addObject("status", ss.searchAll());
		mv.addObject("oferta", new Oferta());
		return mv;
	}

	@GetMapping("/ofertas")
	public ModelAndView listaOferta() {
		System.out.println(dateUtil.formatLocaldateTimeToDatabaseStyle(LocalDateTime.now()));
		ModelAndView mv = new ModelAndView("oferta/tabelaOferta");
		mv.addObject("ofertas", os.searchAll());
		return mv;
	}

	@GetMapping("/procuraOferta")
	public Oferta procuraOferta(Integer id) throws ObjectNotFoundException {
		System.out.println(dateUtil.formatLocaldateTimeToDatabaseStyle(LocalDateTime.now()));
		return os.search(id);
	}

	@GetMapping(value = "/alteraOferta/{id}")
	public ModelAndView alteraOferta(@PathVariable("id") Integer id) throws ObjectNotFoundException {
		System.out.println(dateUtil.formatLocaldateTimeToDatabaseStyle(LocalDateTime.now()));
		ModelAndView mv = new ModelAndView("oferta/alteraOfertaModal");
		mv.addObject("oferta", os.search(id));
		mv.addObject("status", ss.searchAll());
		mv.addObject("produtos", ps.searchAll());
		return mv;
	}

	@PostMapping("/alteraOferta")
	public ModelAndView alteraOferta(Oferta oferta) throws ObjectNotFoundException {
		os.edit(oferta);
		return listaOferta();
	}

	@PostMapping("/salvaOferta")
	public String salvaOferta(Oferta oferta, RedirectAttributes redirectAttributes) {
		os.save(oferta);
		redirectAttributes.addFlashAttribute("msg_resultado", "Oferta cadastrada com sucesso!");
		return "redirect:/cadastraOferta";
	}

	@GetMapping("/visualizaOferta/{id}")
	public ModelAndView deletaOferta(@PathVariable("id") Integer id) throws ObjectNotFoundException {
		ModelAndView mv = new ModelAndView("oferta/visualizaOfertaModal");
		mv.addObject("oferta", os.search(id));
		return mv;
	}
}