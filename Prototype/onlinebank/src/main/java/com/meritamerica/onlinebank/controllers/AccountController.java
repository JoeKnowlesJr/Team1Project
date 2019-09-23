package com.meritamerica.onlinebank.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.meritamerica.onlinebank.dto.DashModel;
import com.meritamerica.onlinebank.models.Account;
import com.meritamerica.onlinebank.models.AccountType;
import com.meritamerica.onlinebank.models.CdAccount;
import com.meritamerica.onlinebank.models.DbaCheckingAccount;
import com.meritamerica.onlinebank.models.PersonalCheckingAccount;
import com.meritamerica.onlinebank.models.RegularIra;
import com.meritamerica.onlinebank.models.RolloverIra;
import com.meritamerica.onlinebank.models.RothIra;
import com.meritamerica.onlinebank.models.SavingsAccount;
import com.meritamerica.onlinebank.models.Transaction;
import com.meritamerica.onlinebank.models.TransactionType;
import com.meritamerica.onlinebank.models.User;
import com.meritamerica.onlinebank.models.UserSession;
import com.meritamerica.onlinebank.services.AccountService;
import com.meritamerica.onlinebank.services.TransactionService;
import com.meritamerica.onlinebank.services.UserService;

@Controller
@Scope("request")
public class AccountController {
	@Autowired private UserService uService;
	@Autowired private AccountService aService;
	@Autowired private TransactionService tService;
	@Autowired UserSession uSession;
	
	public AccountController() {}
	
	@PostMapping("/openAccount")
	public String openAccount(Model model, @ModelAttribute("dm") DashModel dm) {
		model.addAttribute("dm", dm);
		return "open.jsp";
	}
	
	@PostMapping("/createAccount")
	public String createAccount(@ModelAttribute("dm") DashModel dm, Model model) {
		User u = null;
		Optional<User> oU = uService.findUserById(dm.getNafo().getUser_id());
		if (oU.isPresent()) {
			u = (User)oU.get();
		} else {
			u = new User();
		}
		List<Account> userAccounts = u.getAccounts();
		Account a = null;
		switch(dm.nafo.getAcctType()) {
		
		case SAVINGS:
			for (Account aa : userAccounts) {
				if (aa instanceof SavingsAccount) {
					dm.error = "Only one savings account allowed per account holder!";
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
					dm.error = "Only one personal checking account allowed per account holder!";
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
					dm.error = "Only three DBA checking accounts allowed per account holder!";
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
			List<Account> cds = new ArrayList<>();
			for (Account aa : userAccounts) {
				if (aa instanceof CdAccount) { cds.add(((CdAccount) aa)); }
				//Add display of all CD accounts
			}
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
			a = new RothIra(
					System.currentTimeMillis() / 10000,
					0.0,
					0.0,
					u
				);				
			break;
		case ROLLIRA:
			a = new RolloverIra(
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
		if (!dm.error.isEmpty()) {
			model.addAttribute("error", true);
			return "dashboard/dashboard.jsp";
		}
		Transaction t = new Transaction(TransactionType.DEPOSIT, dm.nafo.getAmount(), Account.CASH, a, "Initial Deposit");
		tService.saveTransaction(t);
		a.transact(t);
		aService.createAccount(a);
		u.addAccount(a);
		uService.updateUser(u);
		
		dm.account = a;
		model.addAttribute("dm", dm);
		return "dashboard/dashboard.jsp";		
	}
	
	@PostMapping("/closeAccount")
	public String closeAccount(Model model, @ModelAttribute("dm") DashModel dm) {
		dm.error = "";
		Long acctToClose = dm.cafo.getAcctToClose();
		Long balanceTarget = dm.cafo.getBalanceTarget();
		Optional<Account> oA = aService.findByAccountNumber(acctToClose);
		Optional<Account> oB = aService.findByAccountNumber(balanceTarget);
		Account a = null, b = null;
		if (acctToClose == balanceTarget) {
			dm.error = "Balance must be deposited into different account!";
		}
		if (oA.isPresent()) {
			a = oA.get();
		} else {
			dm.error = "Account to close not found!";
		}
		if (oB.isPresent()) {
			b = oB.get();
		} else {
			dm.error = "Target account for balance deposit not found!";
		}
		if (dm.error.length() > 0) {
			model.addAttribute("dm", dm);
			return "dashboard/close.jsp";
		}
		Transaction tClose = new Transaction(TransactionType.WITHDRAWL, a.getBalance(), Account.CASH, a, "Closed Account");
		Transaction tDep = new Transaction(TransactionType.DEPOSIT, a.getBalance(), Account.CASH, b, "From closing account: " + acctToClose);
		
		a.transact(tClose);
		b.transact(tDep);
		
		a.setAccountType(AccountType.CLOSED);
//		aService.updateAccount(a);
//		aService.updateAccount(b);
		uService.updateUser(b.getUser());
		model.addAttribute("dm", dm);
		return "redirect:/dashboard";
	}
}
