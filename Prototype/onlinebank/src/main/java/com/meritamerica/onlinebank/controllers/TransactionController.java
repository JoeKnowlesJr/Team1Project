package com.meritamerica.onlinebank.controllers;

import com.meritamerica.onlinebank.services.TransactionService;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.meritamerica.onlinebank.dto.DashModel;
import com.meritamerica.onlinebank.models.Transaction;
import com.meritamerica.onlinebank.models.UserSession;
import com.meritamerica.onlinebank.services.UserService;

@Controller
public class TransactionController {
	@Autowired private UserService uService;
	@Autowired private TransactionService tService;
	
	public TransactionController(UserService u) { uService = u; }

	@PostMapping("/transaction")
	public String transact(Model model, @ModelAttribute("dm") DashModel dm, BindingResult result) {
		if (result.hasErrors()) {
			dm.setTfoError(true);
			model.addAttribute("dm", dm);
			return "/dashboard/dashboard.jsp";
		}
		Transaction t = dm.tfo.getTransaction(dm.account);
		tService.saveTransaction(t);
		dm.account.transact(t);
		uService.updateUser(dm.account.getUser());
		dm.setTfoError(false);
		model.addAttribute("dm", dm);
		return "/dashboard/dashboard.jsp";
	}
	
	@PostMapping("/transaction/{tType}")
	public String transactionSelect(HttpServletRequest request, Model model, @ModelAttribute("dm") DashModel dm, @PathVariable("tType") String tType) {
		String view = "";
		switch(tType) {
		case "Transfer":
			view = "/dashboard/ttView.jsp";
			break;
		case "Deposit":
			view = "/dashboard/tdView.jsp";
			break;
		case "Withdrawl":
			view = "/dashboard/twView.jsp";
			break;
		}
		model.addAttribute("accounts", ((UserSession)request.getSession().getAttribute("user-session")).getUser().getAccounts());
		model.addAttribute("dm", dm);
		return view;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
