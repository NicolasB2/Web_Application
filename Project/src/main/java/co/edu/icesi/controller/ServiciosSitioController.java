package co.edu.icesi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.icesi.delegate.DelegateBus;
import co.edu.icesi.delegate.DelegateConductor;
import co.edu.icesi.delegate.DelegateRuta;
import co.edu.icesi.delegate.DelegateServiciosSitio;
import co.edu.icesi.delegate.DelegateSitio;
import co.edu.icesi.model.Tmio1Servicio;
import co.edu.icesi.model.Tmio1ServiciosSitio;

@Controller
public class ServiciosSitioController {

	private DelegateServiciosSitio delegateServiciosSitio;
	private	DelegateBus delegateBus;
	private DelegateSitio delegateSitio;
	private DelegateConductor delegateConductor;
	private DelegateRuta delegateRuta;
	
	
	@Autowired
	public ServiciosSitioController(DelegateServiciosSitio delegateServiciosSitio, DelegateBus delegateBus, DelegateConductor delegateConductor, DelegateRuta delegateRuta, DelegateSitio delegateSitio ) {
		
		this.delegateServiciosSitio = delegateServiciosSitio;
		this.delegateBus = delegateBus;
		this.delegateConductor = delegateConductor;
		this.delegateRuta = delegateRuta;
		this.delegateSitio = delegateSitio;
	}
	
	@RequestMapping(value = "/servicios", method = RequestMethod.GET)
	public String serviciosSitio(Model model) {
		model.addAttribute("serviciosSitios", delegateServiciosSitio.getTmioServiciosSitios());
		return "serviciosSitios/index";
	}
	
	
	@GetMapping("/serviciosSitios/add-serviciosSitio")
	public String busesAdd(Model model) {
		model.addAttribute("buses", delegateBus.getTmioBuses());
		model.addAttribute("conductores", delegateConductor.getTmioConductores());
		model.addAttribute("rutas", delegateRuta.getTmioRutas());
		model.addAttribute("sitios", delegateSitio.getTmioSitios());
		model.addAttribute("tmio1ServiciosSitio", new Tmio1ServiciosSitio());
		return "serviciosSitios/add-serviciosSitio";
	}
	
	@PostMapping("/serviciosSitios/add-serviciosSitio")
	public String saveApps(@RequestParam(value = "action", required = true) String action,
			@Valid Tmio1ServiciosSitio tmio1ServiciosSitios, BindingResult bindingResult, Model model) {
		if (!action.equals("Cancel")) {
			if (bindingResult.hasErrors()) {
				
				model.addAttribute("buses", delegateBus.getTmioBuses());
				model.addAttribute("conductores", delegateConductor.getTmioConductores());
				model.addAttribute("rutas", delegateRuta.getTmioRutas());
				model.addAttribute("sitios", delegateSitio.getTmioSitios());
				
				System.out.println(bindingResult.getAllErrors().get(0));
				
				return "serviciosSitios/add-serviciosSitio";
			} else
				
				try {
					delegateServiciosSitio.addTmioServiciosSitio(tmio1ServiciosSitios);
				} catch (Exception  e) {
					e.printStackTrace();
					System.out.println(bindingResult.getAllErrors().get(0));
					return "redirect:/error";
				}

			model.addAttribute("buses", delegateBus.getTmioBuses());
			model.addAttribute("conductores", delegateConductor.getTmioConductores());
			model.addAttribute("rutas", delegateRuta.getTmioRutas());
			model.addAttribute("sitios", delegateSitio.getTmioSitios());
		}
		return "redirect:/serviciosSitios";
	}
	
	@GetMapping("/serviciosSitios/update-serviciosSitio/{id_hash}")
	public String showUpdateApps(@PathVariable("id_hash") Integer id_hash, Model model) {

		Iterable<Tmio1ServiciosSitio> servicios = delegateServiciosSitio.getTmioServiciosSitios();
		Tmio1ServiciosSitio servicio = null;
		
		for (Tmio1ServiciosSitio ser : servicios) {
			if (ser.getId_hash().compareTo(id_hash) == 0) {
				delegateServiciosSitio.delTmioServiciosSitio(ser);
				servicio = ser;
				break;
			}
		}
	
		model.addAttribute("buses", delegateBus.getTmioBuses());
		model.addAttribute("conductores", delegateConductor.getTmioConductores());
		model.addAttribute("rutas", delegateRuta.getTmioRutas());
		model.addAttribute("sitios", delegateSitio.getTmioSitios());
		model.addAttribute("tmio1ServiciosSitio", servicio);
		return "serviciosSitios/update-serviciosSitio";	
	}
	
	
	@PostMapping("/serviciosSitios/update-serviciosSitio/{id_hash}")
	public String updateApp(@PathVariable("id_hash") Integer id_hash,
			@RequestParam(value = "action", required = true) String action, @Valid Tmio1ServiciosSitio tmio1ServiciosSitio,
			BindingResult bindingResult, Model model) {
		if (!action.equals("Cancel")) {
			if (bindingResult.hasErrors()) {
				return "/serviciosSitios/update-serviciosSitio";
			}
		}
		
		try {
			delegateServiciosSitio.addTmioServiciosSitio(tmio1ServiciosSitio);
		} catch (Exception  e) {
			e.printStackTrace();
			System.out.println(bindingResult.getAllErrors().get(0));
			return "redirect:/error";
		}

		model.addAttribute("buses", delegateBus.getTmioBuses());
		model.addAttribute("conductores", delegateConductor.getTmioConductores());
		model.addAttribute("rutas", delegateRuta.getTmioRutas());
		model.addAttribute("sitios" , delegateSitio.getTmioSitios());

		return "redirect:/serviciosSitios";
	}
	
	
	@GetMapping("/serviciosSitios/filter-serviciosSitio")
	public String consultForm(Model model) {

		model.addAttribute("tmio1ServiciosSitio", new Tmio1ServiciosSitio());
		return "serviciosSitios/filter-serviciosSitio";
	}
	
	
}
