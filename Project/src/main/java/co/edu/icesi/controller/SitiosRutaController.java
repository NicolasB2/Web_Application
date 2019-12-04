package co.edu.icesi.controller;

import javax.validation.Valid;

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

import co.edu.icesi.delegate.DelegateRuta;
import co.edu.icesi.delegate.DelegateSitio;
import co.edu.icesi.delegate.DelegateSitiosRuta;
import co.edu.icesi.model.Tmio1Bus;
import co.edu.icesi.model.Tmio1Ruta;
import co.edu.icesi.model.Tmio1Sitio;
import co.edu.icesi.model.Tmio1SitiosRuta;
import co.edu.icesi.model.Tmio1SitiosRutaPK;

@Controller
public class SitiosRutaController {

	private DelegateSitiosRuta delegateSitiosRuta;
	private DelegateSitio delegateSitio;
	private DelegateRuta delegateRuta;

	@Autowired
	public SitiosRutaController(DelegateSitiosRuta delegateSitiosRuta, DelegateSitio delegateSitio,
			DelegateRuta delegateRuta) {
		this.delegateSitiosRuta = delegateSitiosRuta;
		this.delegateSitio = delegateSitio;
		this.delegateRuta = delegateRuta;
	}

	@RequestMapping(value = "/sitiosRutas", method = RequestMethod.GET)
	public String sitiosRutas(Model model) {
		model.addAttribute("sitios", delegateSitiosRuta.getTmioSitiosRutas());
		return "sitiosRutas/index";
	}

	@GetMapping("/sitiosRutas/add-sitiosRuta")
	public String sitiosRutaAdd(Model model) {

		model.addAttribute("sitios", delegateSitio.getTmioSitios());
		model.addAttribute("rutas", delegateRuta.getTmioRutas());
		model.addAttribute("tmio1SitiosRuta", new Tmio1SitiosRuta());
		return "sitiosRutas/add-sitiosRuta";
	}

	@PostMapping("/sitiosRutas/add-sitiosRuta")
	public String saveApps(@RequestParam(value = "action", required = true) String action,
			@Valid Tmio1SitiosRuta tmio1SitiosRuta, BindingResult bindingResult, Model model) {

		if (!action.equals("Cancel")) {
			if (bindingResult.hasErrors()) {

				model.addAttribute("rutas", delegateRuta.getTmioRutas());
				model.addAttribute("sitios", delegateSitio.getTmioSitios());
				return "sitiosRutas/add-sitiosRuta";
			} else {
				try {
					
					tmio1SitiosRuta.setTmio1Ruta1(delegateRuta.getTmioRuta(tmio1SitiosRuta.getIdruta()));
					tmio1SitiosRuta.setTmio1Sitio1(delegateSitio.getTmioSitio(tmio1SitiosRuta.getIdsitio()));
					
					delegateSitiosRuta.addTmioSitioRuta(tmio1SitiosRuta);

				} catch (Exception e) {
					e.printStackTrace();
					return "redirect:/error";
				}
			}
			model.addAttribute("rutas", delegateRuta.getTmioRutas());
			model.addAttribute("sitios", delegateSitio.getTmioSitios());

		}

		return "redirect:/sitiosRutas";

	}

}
