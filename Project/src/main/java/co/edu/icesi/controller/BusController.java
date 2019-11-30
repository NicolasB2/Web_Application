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

import co.edu.icesi.delegate.DelegateBus;
import co.edu.icesi.model.Tmio1Bus;
import co.edu.icesi.services.BusService;

@Controller
public class BusController {

	private DelegateBus delegateBus;

	@Autowired
	public BusController(DelegateBus delegateBus) {
		this.delegateBus = delegateBus;
	}

	@RequestMapping(value = "/buses", method = RequestMethod.GET)
	public String buses(Model model) {
		model.addAttribute("buses", delegateBus.getTmioBuses());
		return "buses/index";
	}

	@GetMapping("/buses/add-bus")
	public String busesAdd(Model model) {
		model.addAttribute("tmio1Bus", new Tmio1Bus());
		model.addAttribute("types", delegateBus.getType());
		
		return "buses/add-bus";
	}

	@PostMapping("/buses/add-bus")
	public String saveApps(@RequestParam(value = "action", required = true) String action, @Validated Tmio1Bus tmio1Bus,
			BindingResult bindingResult, Model model) {
		if (!action.equals("Cancel")) {
			if (bindingResult.hasErrors()) {
				model.addAttribute("types", delegateBus.getType());
				return "buses/add-bus";
			} else {
				try {
					delegateBus.addTmioBus(tmio1Bus);
					//service.save(tmio1Bus);
				} catch (Exception  e) {
					e.printStackTrace();
					System.out.println(bindingResult.getAllErrors().get(0));
					return "redirect:/error";
				}
			}
			model.addAttribute("types", delegateBus.getType());
		}
		return "redirect:/buses";
	}

	@GetMapping("/buses/consult-bus")
	public String consultForm(Model model) {
		model.addAttribute("tmio1Bus", new Tmio1Bus());
		return "buses/consult-bus";
	}

	@PostMapping("/buses/consult-bus")
	public String showConsultForm2(@ModelAttribute Tmio1Bus tmio1Bus, Model model) {

		
		Tmio1Bus b = delegateBus.getTmioBus(tmio1Bus.getId());
		if (b == null) {

		}
		model.addAttribute("tmio1Bus", b);
		return "buses/edit-bus";
	}
}
