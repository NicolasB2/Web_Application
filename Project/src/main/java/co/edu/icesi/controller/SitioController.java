package co.edu.icesi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.icesi.delegate.DelegateRuta;
import co.edu.icesi.delegate.DelegateServiciosSitio;
import co.edu.icesi.delegate.DelegateSitio;
import co.edu.icesi.delegate.IDelegateRuta;
import co.edu.icesi.delegate.IDelegateServiciosSitio;
import co.edu.icesi.delegate.IDelegateSitio;
import co.edu.icesi.model.Tmio1Bus;
import co.edu.icesi.model.Tmio1Sitio;

@Controller
public class SitioController {

	@Autowired
	private IDelegateSitio delegateSitio;
	@Autowired
	private IDelegateServiciosSitio delegateServiciosSitio;
	@Autowired
	private IDelegateRuta delegateRuta;
	
	

	@RequestMapping(value = "/sitios", method = RequestMethod.GET)
	public String buses(Model model) {
		model.addAttribute("sitios", delegateSitio.getTmioSitios());
		return "sitios/index";
	}
	
	@GetMapping("/sitios/add-sitio")
	public String sitiosAdd(Model model) {
		model.addAttribute("tmio1Sitio", new Tmio1Sitio());
		model.addAttribute("tmio1serviciositio", delegateServiciosSitio.getTmioServiciosSitios());
		model.addAttribute("tmio1sitiosruta", delegateRuta.getTmioRutas());
		return "sitios/add-sitio";
	}
	

	@PostMapping("/sitios/add-sitio")
	public String saveApps(@RequestParam(value = "action", required = true) String action, @Validated Tmio1Sitio tmio1Sitio,
			BindingResult bindingResult, Model model) throws Exception{
		if (!action.equals("Cancel")) {
			if (bindingResult.hasErrors()) {
				
				model.addAttribute("tmio1serviciositio", delegateServiciosSitio.getTmioServiciosSitios());
				model.addAttribute("tmio1sitiosruta", delegateRuta.getTmioRutas());
				
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
			
			model.addAttribute("tmio1serviciositio", delegateServiciosSitio.getTmioServiciosSitios());
			model.addAttribute("tmio1sitiosruta", delegateRuta.getTmioRutas());
			
		}
		return "redirect:/sitios";
	}
	
	
	@GetMapping("/sitios/consult-sitio")
	public String consultForm(Model model) {
		model.addAttribute("tmio1Sitio", new Tmio1Sitio());
		return "sitios/consult-sitio";
	}
	
	@PostMapping("/sitios/consult-sitio")
	public String showConsultForm2(@ModelAttribute Tmio1Sitio tmio1Sitio, Model model) {

		
		Tmio1Sitio b = delegateSitio.getTmioSitio(tmio1Sitio.getId());
		model.addAttribute("tmio1Sitio", b);
		return "sitios/edit-sitio";
	}
	
	
	
	
	
}
