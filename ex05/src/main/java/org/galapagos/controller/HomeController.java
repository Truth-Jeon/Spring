package org.galapagos.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.galapagos.service.TravelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.log4j.Log4j;

/**
 * Handles requests for the application home page.
 */
@Controller
@Log4j
public class HomeController {
	@Autowired
	private TravelService travelService;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String home(Model model) {
		model.addAttribute("travels", travelService.getRandom(5));
		return "home";
	}
	
}
