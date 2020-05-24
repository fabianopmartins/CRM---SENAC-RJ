package br.com.senac.crm.controllers;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.senac.crm.models.Status;
import br.com.senac.crm.service.StatusServiceImpl;
import br.com.senac.crm.util.DateUtil;
import javassist.tools.rmi.ObjectNotFoundException;

@Controller
public class StatusController {

	@Autowired
	private DateUtil dateUtil;

	@Autowired
	private StatusServiceImpl ss;

	@GetMapping("/cadastraStatus")
	public ModelAndView formCadastraStatus() {
		System.out.println(dateUtil.formatLocaldateTimeToDatabaseStyle(LocalDateTime.now()));
		ModelAndView mv = new ModelAndView("status/formStatusModal");
		mv.addObject("status", new Status());
		return mv;
	}

	@GetMapping("/status")
	public ModelAndView listaStatus() {
		System.out.println(dateUtil.formatLocaldateTimeToDatabaseStyle(LocalDateTime.now()));
		ModelAndView mv = new ModelAndView("status/tabelaStatus");
		mv.addObject("status", ss.searchAll());
		return mv;
	}

	@GetMapping("/procuraStatus")
	public Status procuraStatus(Integer id) throws ObjectNotFoundException {
		System.out.println(dateUtil.formatLocaldateTimeToDatabaseStyle(LocalDateTime.now()));
		return ss.search(id);
	}

	@GetMapping("/alteraStatus/{id}")
	public ModelAndView alteraStatus(@PathVariable("id") Integer id) throws ObjectNotFoundException {
		System.out.println(dateUtil.formatLocaldateTimeToDatabaseStyle(LocalDateTime.now()));
		ModelAndView mv = new ModelAndView("status/alteraStatusModal");
		mv.addObject("status", ss.search(id));
		return mv;
	}

	@PostMapping("/alteraStatus")
	public ModelAndView alteraStatus(Status status) throws ObjectNotFoundException {
		ss.edit(status);
		return listaStatus();
	}

	@PostMapping("/salvaStatus")
	public String salvaStatus(Status status, RedirectAttributes redirectAttributes) {
		ss.save(status);
		redirectAttributes.addFlashAttribute("msg_resultado", "Status cadastrado com sucesso!");
		return "redirect:/cadastraStatus";
	}
}