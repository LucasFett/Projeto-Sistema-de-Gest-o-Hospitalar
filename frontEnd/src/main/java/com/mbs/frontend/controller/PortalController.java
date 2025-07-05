package com.mbs.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PortalController { 

   
   @GetMapping("/pacientes")
	public String pacientes(Model model) {		
		return "pacientes"; 
	}
   
   
   @GetMapping("/agendamentos")
	public String agendamentos(Model model) {		
		return "agendamentos"; 
	}
}