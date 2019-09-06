package com.meritamerica.onlinebank.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.meritamerica.onlinebank.models.Account;
import com.meritamerica.onlinebank.models.CdAccount;
import com.meritamerica.onlinebank.models.DashModel;
import com.meritamerica.onlinebank.models.DbaCheckingAccount;
import com.meritamerica.onlinebank.models.NewAccountFormObject;
import com.meritamerica.onlinebank.models.PersonalCheckingAccount;
import com.meritamerica.onlinebank.models.RegularIra;
import com.meritamerica.onlinebank.models.SavingsAccount;
import com.meritamerica.onlinebank.models.Transaction;
import com.meritamerica.onlinebank.models.TransactionType;
import com.meritamerica.onlinebank.models.User;
import com.meritamerica.onlinebank.services.BankService;

@Controller
public class AccountController {
	private final BankService service;
	
	public AccountController(BankService b) { service = b; }
	
	@PostMapping("/open")
	public String open(Model model) {
		List<User> uList = service.allUsers();
		Map<String, String> map = new HashMap<>();
		for (User u : uList) {
			map.put(String.valueOf(u.getId()), 
					String.format("%s %s", u.getFirstName(), u.getLastName()));
		}
		model.addAttribute("users", map);
		model.addAttribute("nafo", new NewAccountFormObject());
		return "open.jsp";
	}
	
	@PostMapping("/createAccount")
	public String createAccount(@ModelAttribute("dm") DashModel dm, Model model) {
		User u = null;
		Optional<User> oU = service.findUserById(dm.getNafo().getUser_id());
		if (oU.isPresent()) {
			u = (User)oU.get();
		} else {
			u = new User();
		}
		Set<Account> userAccounts = u.getAccounts();
		Account a = null;
		switch(dm.getNafo().getAcctType()) {
		
		case SAVINGS:
			for (Account aa : userAccounts) {
				if (aa instanceof SavingsAccount) {
					dm.setError("Only one savings account allowed per account holder!");
					break;
				}
			}
			a = new SavingsAccount(
						System.currentTimeMillis() / 10000,
						0.0,
						1.2,
						u
					);
			break;
		case CHECKING:
			for (Account aa : userAccounts) {
				if (aa instanceof PersonalCheckingAccount) {
					dm.setError("Only one personal checking account allowed per account holder!");
					break;
				}
			}
			a = new PersonalCheckingAccount(
						System.currentTimeMillis() / 10000,
						0.0,
						0.6,
						u
					);
			break;
		case DBACHECK:
			int dbas = 0;
			for (Account aa : userAccounts) {
				if (aa instanceof DbaCheckingAccount) { ++dbas; }
				if (dbas > 2) {
					dm.setError("Only three DBA checking accounts allowed per account holder!");
					break;
				}
			}
			a = new DbaCheckingAccount(
						System.currentTimeMillis() / 10000,
						0.0,
						0.6,
						u
					);
			break;
		case CD:
//			int cds = 0;
//			for (Account aa : userAccounts) {
//				if (aa instanceof CdAccount) { ++cds; }
//				Add display of all CD accounts
//			}
			a = new CdAccount(
					System.currentTimeMillis() / 10000,
					0.0,
					0.0,
					u
				);			
			break;
		case REGIRA:
			a = new RegularIra(
					System.currentTimeMillis() / 10000,
					0.0,
					0.0,
					u
				);				
			break;
		case ROTHIRA:
			a = new CdAccount(
					System.currentTimeMillis() / 10000,
					0.0,
					0.0,
					u
				);				
			break;
		case ROLLIRA:
			a = new CdAccount(
					System.currentTimeMillis() / 10000,
					0.0,
					0.0,
					u
				);				
			break;
		case CLOSED:
			break;
		default:
			break;
		}
		String err = dm.getError();
		if (!err.isEmpty()) {
			model.addAttribute("error", err);
			return "open.jsp";
		}
		a.deposit(new Transaction(TransactionType.DEPOSIT, dm.getNafo().getAmount(), Account.CASH, a.getAccountNumber(), a));
		u.addAccount(a);
		service.updateUser(u);
		
		model.addAttribute("account", a);
		return "dashboard/dashboard.jsp";		
	}	
	
	
}
