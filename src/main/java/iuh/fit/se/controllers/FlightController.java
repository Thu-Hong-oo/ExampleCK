package iuh.fit.se.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import iuh.fit.se.models.Airplane;
import iuh.fit.se.models.Flight;
import iuh.fit.se.models.FlightStatus;
import iuh.fit.se.repositories.AirPlaneRepository;
import iuh.fit.se.services.FlightService;
import jakarta.validation.Valid;

@Controller
@RequestMapping({"/flights","/"})
public class FlightController {
	
	@Autowired
	private FlightService flightService;
	@Autowired
    private AirPlaneRepository airplaneRepository;

	@GetMapping
	public String listFlights(Model model) {
		List<Flight> list = flightService.getAllFlights();
		model.addAttribute("flights",list);
		return "index";
	}
	
	@GetMapping("/add")
	public String showAddForm(Model model) {
	
		List<Airplane> airplanes = airplaneRepository.findAll();
        List<FlightStatus> flightStatuses = List.of(FlightStatus.values()); 
    	model.addAttribute("airplanes", airplanes);
        model.addAttribute("flightStatuses", flightStatuses);
    	model.addAttribute("flight",new Flight());
    	
		return "add";
	}
	@PostMapping("/add")
	public String addFlight (@Valid @ModelAttribute("flight") Flight flight, BindingResult result, Model model) {
//	 if(result.hasErrors()) {
//		 return "add";
//	 }
//	 flightService.saveFlight(flight);
//	 return "redirect:/flights";
		 try {
	            flightService.saveFlight(flight);
	            return "redirect:/flights";
	        } catch (IllegalArgumentException e) {
	            model.addAttribute("errorMessage", e.getMessage());
	            return "add";
	        }
		
	}
	@PostMapping("/delete/{id}")
	public String deleteFlight (@PathVariable Long id) {
		flightService.deleteFlight(id);
		return "redirect:/flights";
	}
}
