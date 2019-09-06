package com.meritamerica.onlinebank.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.meritamerica.onlinebank.models.Account;
import com.meritamerica.onlinebank.models.DashModel;
import com.meritamerica.onlinebank.services.BankService;

@Controller
public class TransactionController {
	private final BankService service;
	
	public TransactionController(BankService b) { service = b; }

	@PostMapping("/transaction")
	public String transact(Model model, @ModelAttribute("dm") DashModel dm, BindingResult result) {
		if (result.hasErrors()) {
			dm.setFailed(true);
			model.addAttribute("dm", dm);
			return "/dashboard/dashboard.jsp";
		}
		Account a = dm.getAccount();
		switch(dm.getTfo().getType()) {
		case WITHDRAWL:
			a.withdraw(dm.getTfo().getTransaction());
		case DEPOSIT:
			a.deposit(dm.getTfo().getTransaction());
			break;
		case TRANSFER:
			break;
		default:
			break;
		}
		service.updateUser(a.getUser());
		dm.setFailed(false);
		model.addAttribute("dm", dm);
		return "/dashboard/dashboard.jsp";
	}	
	
	public String test(String s) {
		if (s.isEmpty()) return s;
		return test(s.substring(1)) + s.charAt(0);
	}
}
