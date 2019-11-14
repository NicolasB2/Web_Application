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

import co.edu.icesi.exceptions.ConductorNullException;
import co.edu.icesi.exceptions.FechaNullException;
import co.edu.icesi.exceptions.FechasNoConsistentesException;
import co.edu.icesi.exceptions.FormatoIncorrectoException;
import co.edu.icesi.model.Tmio1Conductore;
import co.edu.icesi.services.ConductorService;

@Controller
public class ConductorController {

	private ConductorService service;

	@Autowired
	public ConductorController(ConductorService service) {
		this.service = service;
	}

	@RequestMapping(value = "/conductores", method = RequestMethod.GET)
	public String conductores(Model model) {
		model.addAttribute("conductores", service.findAll());
		return "conductores/index";
	}

	@GetMapping("/conductores/add-conductore")
	public String conductoresAdd(Model model) {

		model.addAttribute("tmio1Conductore", new Tmio1Conductore());
		return "conductores/add-conductore";
	}

	@PostMapping("/conductores/add-conductore")
	public String saveApps(@RequestParam(value = "action", required = true) String action,@Validated Tmio1Conductore tmio1Conductore,
			BindingResult bindingResult, Model model) {
		
		if (!action.equals("Cancel")) {
			if (bindingResult.hasErrors()) {
				return "conductores/add-conductore";
			}
			else {
				try {
					service.save(tmio1Conductore);
				} catch (ConductorNullException | FechasNoConsistentesException | FechaNullException
						| FormatoIncorrectoException e) {
					return "redirect:/error";
				}
			}
		}
		return "redirect:/conductores";
	}

	
	
	@GetMapping("/conductores/consult-conductore")
	public String consultForm(Model model) {

		model.addAttribute("tmio1Conductore", new Tmio1Conductore());
		return "conductores/consult-conductore";
	}
	
	
	@PostMapping("/conductores/consult-conductore")
	public String showConsultForm2(@ModelAttribute Tmio1Conductore tmio1Conductore, Model model) {

		Tmio1Conductore c = service.findById(tmio1Conductore.getCedula());
		if (c == null) {
			
		}
		
		model.addAttribute("tmio1Conductore", c);
		return "conductores/edit-conductore";
	}
	
	
}
