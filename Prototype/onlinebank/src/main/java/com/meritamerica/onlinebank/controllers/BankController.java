package com.meritamerica.onlinebank.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.meritamerica.onlinebank.models.Account;
import com.meritamerica.onlinebank.models.DashModel;
import com.meritamerica.onlinebank.models.LoginFormObject;
import com.meritamerica.onlinebank.models.User;
import com.meritamerica.onlinebank.models.UserFormObj;
import com.meritamerica.onlinebank.services.BankService;

@Controller
public class BankController {
	private final BankService service;
	
	public BankController(BankService b) { service = b; }
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("lfo", new LoginFormObject());
		return "index.jsp";
	}
	
	@PutMapping("/")
	public String add(Model model, @Valid @ModelAttribute("user") User user, BindingResult result) {
        if (result.hasErrors()) {
            return "index.jsp";
        } else {
        	service.createUser(user);
            return "redirect:/";
        }
	}
	
	@PostMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("ufo", new UserFormObj());
		return "signup.jsp";
	}
	
	
	@PutMapping("/createUser")
	public String create(Model model, @Valid @ModelAttribute("ufo") UserFormObj user, BindingResult result) {
        if (result.hasErrors()) {
            return "signup.jsp";
        } 
    	User u = user.getUser();
    	service.createUser(u);
        return "redirect:/";
	}
	
	@PostMapping("/signin")
	public String signin(Model model, @Valid @ModelAttribute("lfo") LoginFormObject lfo,  BindingResult result) {
        if (result.hasErrors()) {
        	lfo.setFailed(true);
        	model.addAttribute("lfo", lfo);
            return "index.jsp";
        } 
		Optional<User> oU = service.findUserByEmail(lfo.getEmail());
		if (oU.isPresent()) {
			DashModel dm = new DashModel();
			User u = (User) oU.get();
			if (u.auth(lfo.getPassword())) {
				dm.setUser(u);
				if (u.hasAccounts()) {
					dm.setAccount(u.getAccounts().toArray(new Account[0])[0]);
				}
				model.addAttribute("dm", dm);
				return "/dashboard/dashboard.jsp";
			}			
		}
    	lfo.setFailed(true);
    	model.addAttribute("lfo", lfo);
		return "index.jsp";
	}
	
	/////////////////////////////////////////////
	
	@GetMapping("/user-test")
	public String user_test(Model model) {
		List<User> uList = service.allUsers();
		model.addAttribute("users", uList);
		return "user-test.jsp";
	}
	
	//////////////////////////////////////////////
	
}
