package com.meritamerica.onlinebank.controllers;

import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meritamerica.onlinebank.dto.LoginFormObject;
import com.meritamerica.onlinebank.dto.UserFormObj;

@Controller
public class BankController {
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("lfo", new LoginFormObject());
		return "index.jsp";
	}

	@PostMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("ufo", new UserFormObj());
		return "signup.jsp";
	}
	

	
	/////////////////////////////////////////////

	
	//////////////////////////////////////////////
	
	@PostMapping("/admin")
	public String admin() {
		return "admin.jsp";
	}
	
	
	@RestController
	@RequestMapping("/principal")
	public class UserController {
	 
	    @GetMapping
	    public Principal retrievePrincipal(Principal principal) {
	        return principal;
	    }
	}
	
	
	
	
	
	
	
	
}
