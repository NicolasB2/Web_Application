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
import co.edu.icesi.delegate.DelegateSitiosRuta;
import co.edu.icesi.model.Tmio1Bus;
import co.edu.icesi.model.Tmio1SitiosRuta;

@Controller
public class SitiosRutaController {

	private DelegateSitiosRuta delegateSitiosRuta;
	
	@Autowired
	public SitiosRutaController(DelegateSitiosRuta delegateSitiosRuta) {
		this.delegateSitiosRuta = delegateSitiosRuta;
	}
	
	
	@RequestMapping(value = "/sitiosRutas", method = RequestMethod.GET)
	public String sitiosRutas(Model model) {
		model.addAttribute("sitiosRutas", delegateSitiosRuta.getTmioSitiosRutas());
		return "sitiosRutas/index";
	}
	
	@GetMapping("/sitiosRuta/add-sitiosRuta")
	public String sitiosRutaAdd(Model model) {
		model.addAttribute("tmio1SitiosRuta", new Tmio1SitiosRuta());
		return "sitiosRutas/add-sitiosRuta";
	}
	
	@PostMapping("/sitiosRutas/add-sitiosRuta")
	
	public String saveApps(@RequestParam(value = "action", required = true) String action, @Validated Tmio1SitiosRuta tmio1SitiosRuta,
			BindingResult bindingResult, Model model) {
		if (!action.equals("Cancel")) {
			if (bindingResult.hasErrors()) {
				
				return "buses/add-bus";
			} else {
				try {
					delegateSitiosRuta.addTmioSitioRuta(tmio1SitiosRuta);
				} catch (Exception  e) {
					e.printStackTrace();
					System.out.println(bindingResult.getAllErrors().get(0));
					return "redirect:/error";
				}
			}
		}
		return "redirect:/sitiosRutas";
	}
	
	

	
}
