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
import co.edu.icesi.exceptions.FechaInvalidaException;
import co.edu.icesi.exceptions.FechaNullException;
import co.edu.icesi.exceptions.FechasNoConsistentesException;
import co.edu.icesi.exceptions.HoraInvalidaException;
import co.edu.icesi.exceptions.HoraNullException;
import co.edu.icesi.exceptions.HorasNoConsistentesException;
import co.edu.icesi.exceptions.RutaNullException;
import co.edu.icesi.model.Tmio1Ruta;
import co.edu.icesi.services.RutaService;

@Controller
public class RutasController {

	private DelegateRuta delegateRuta; 
	
	
	@Autowired
	public RutasController(DelegateRuta delegateRuta) {
		this.delegateRuta= delegateRuta;
	}
	

	@RequestMapping(value = "/rutas", method = RequestMethod.GET)
	public String rutas(Model model) {
		model.addAttribute("rutas", delegateRuta.getTmioRutas() );
		return "rutas/index";
	}
	
	@GetMapping("/rutas/add-ruta")
	public String rutasAdd(Model model) {		
		model.addAttribute("tmio1Ruta", new Tmio1Ruta());
		return "rutas/add-ruta";
	}
	
	@PostMapping("/rutas/add-ruta")
	public String saveApps(@RequestParam(value = "action", required = true) String action, @Validated Tmio1Ruta tmio1Ruta,
			BindingResult bindingResult, Model model) {
		if (!action.equals("Cancel")) {
			if (bindingResult.hasErrors()) {
				return "rutas/add-ruta";
			} else {
				
				try {
					delegateRuta.addTmioRuta(tmio1Ruta);
				} catch (Exception e) {	
					e.printStackTrace();
					System.out.println(bindingResult.getAllErrors().get(0));
					return "redirect:/error";
				}				
			}	
		}
		return "redirect:/rutas";
	}

	@GetMapping("/rutas/consult-ruta")
	public String consultForm(Model model) {

		model.addAttribute("tmio1Ruta", new Tmio1Ruta());
		return "rutas/consult-ruta";
	}
	
	@PostMapping("/rutas/consult-ruta")
	public String showConsultForm2(@ModelAttribute Tmio1Ruta tmio1Ruta, Model model) {

		Tmio1Ruta r = delegateRuta.getTmioRuta(tmio1Ruta.getId());
		if (r == null) {
			
		}
		
		model.addAttribute("tmio1Ruta", r);
		return "rutas/edit-ruta";
	}
	
	
	
}

