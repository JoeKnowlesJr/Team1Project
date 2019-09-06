package com.meritamerica.onlinebank.controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.meritamerica.onlinebank.models.Account;
import com.meritamerica.onlinebank.models.DashModel;
import com.meritamerica.onlinebank.services.BankService;

@Controller
public class DashboardController {
	private final BankService service;
	
	public DashboardController(BankService b) { service = b; }
	
	@PostMapping("/dashboard")
	public String dash(Model model, @ModelAttribute("dm") DashModel dm) {
		model.addAttribute("dm", dm);
		return "/dashboard/dashboard.jsp";
	}
	
	@GetMapping("/acctView/{id}")
	public String acctView(Model model, @PathVariable("id") Long id, @ModelAttribute("dm") DashModel dm) {
		Optional<Account> oA = service.findAccountById(id);
		if (oA.isPresent()) {
			dm.setAccount((Account)oA.get());
		}
		model.addAttribute("dm", dm);
		return "/dashboard/acctView.jsp";
	}
}
