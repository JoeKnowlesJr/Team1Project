package com.meritamerica.onlinebank.controllers;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.meritamerica.onlinebank.dto.DashModel;
import com.meritamerica.onlinebank.models.Account;
import com.meritamerica.onlinebank.models.User;
import com.meritamerica.onlinebank.models.UserSession;
import com.meritamerica.onlinebank.services.AccountService;

@Controller
public class DashboardController {
	private final AccountService service;
	private UserSession uSession;
	
	public DashboardController(AccountService b) { service = b; }
	
	@PostMapping("/dashboard")
	public String dash(Model model, @ModelAttribute("dm") DashModel dm) {
		model.addAttribute("dm", dm);
		return "/dashboard/dashboard.jsp";
	}
	
	@GetMapping("/dashboard")
	public String dashGet(HttpServletRequest request, Model model, @ModelAttribute("dm") DashModel dm) {
		uSession = (UserSession) request.getSession().getAttribute("user-session");
		if (uSession != null) dm.setDefaults((User)uSession.getUser());
		model.addAttribute("dm", dm);
		return "/dashboard/dashboard.jsp";
	}
	
	@GetMapping("/dashboard/acctView/{id}")
	public String acctView(Model model, @PathVariable("id") Long id, @ModelAttribute("dm") DashModel dm) {
		Optional<Account> oA = service.findAccountById(id);
		if (oA.isPresent()) {
			dm.account = (Account)oA.get();
		}
		model.addAttribute("dm", dm);
		return "/dashboard/acctView.jsp";
	}
	
	@GetMapping("/dashboard/open")
	public String dOpen(Model model, @ModelAttribute("dm") DashModel dm) {
		model.addAttribute("dm", dm);
		return "/dashboard/open.jsp";
	}
	
	@GetMapping("/dashboard/close")
	public String dClose(HttpServletRequest request, Model model, @ModelAttribute("dm") DashModel dm) {
		uSession = (UserSession) request.getSession().getAttribute("user-session");
		model.addAttribute("dm", dm);
		model.addAttribute("accts", uSession.getUser().getAccounts());
		return "/dashboard/close.jsp";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}