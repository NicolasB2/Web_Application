package co.edu.icesi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.icesi.delegate.DelegateSitio;
import co.edu.icesi.model.Tmio1Bus;
import co.edu.icesi.model.Tmio1Sitio;

@Controller
public class SitioController {

	private DelegateSitio delegateSitio;
	
	@Autowired
	public SitioController(DelegateSitio delegateSitio) {
		this.delegateSitio = delegateSitio;
	}
	
	@RequestMapping(value = "/sitio", method = RequestMethod.GET)
	public String sitios(Model model) {
		model.addAttribute("sitios", delegateSitio.getTmioSitios());
		return "sitios/index";
	}
	
	@GetMapping("/sitios/add-sitio")
	public String sitiosAdd(Model model) {
		model.addAttribute("tmio1Sitio", new Tmio1Sitio());
		return "sitios/add-sitio";
	}
	
	
	@PostMapping("/sitios/add-sitio")
	public String saveApps(@RequestParam(value = "action", required = true) String action, @Validated Tmio1Sitio tmio1Sitio,
			BindingResult bindingResult, Model model) {
		if (!action.equals("Cancel")) {
			if (bindingResult.hasErrors()) {
				
				return "sitios/add-sitio";
			} else {
				try {
					delegateSitio.addTmioSitio(tmio1Sitio);
				} catch (Exception  e) {
					e.printStackTrace();
					System.out.println(bindingResult.getAllErrors().get(0));
					return "redirect:/error";
				}
			}
			
		}
		return "redirect:/sitios";
	}
	
	
	@GetMapping("/sitios/consult-sitio")
	public String consultForm(Model model) {
		model.addAttribute("tmio1Sitio", new Tmio1Sitio());
		return "sitios/consult-sitio";
	}
	
	
}
