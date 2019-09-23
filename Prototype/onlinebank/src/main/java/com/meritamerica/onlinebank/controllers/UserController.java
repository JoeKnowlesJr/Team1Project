package com.meritamerica.onlinebank.controllers;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityExistsException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.meritamerica.onlinebank.dto.DashModel;
import com.meritamerica.onlinebank.dto.LoginFormObject;
import com.meritamerica.onlinebank.dto.UserFormObj;
import com.meritamerica.onlinebank.models.Account;
import com.meritamerica.onlinebank.models.User;
import com.meritamerica.onlinebank.models.UserSession;
import com.meritamerica.onlinebank.services.UserService;

@Controller
@Scope("request")
public class UserController {
	
	@Autowired UserService uService;
	@Autowired UserSession uSession;
	
	@PutMapping("/createUser")
	public String create(Model model, @ModelAttribute("ufo") @Valid UserFormObj ufo, BindingResult result) {
        if (result.hasErrors()) {
        	model.addAttribute("error", true);
        	model.addAttribute("ufo", ufo);
            return "signup.jsp";
        }
		User u = createUserAccount(ufo, result);
	    if (u == null) {
	        result.rejectValue("email", "That email address is already in use!");
        	model.addAttribute("error", true);
        	model.addAttribute("ufo", ufo);
            return "signup.jsp";	        
	    }
	    
        return "redirect:/";
	}
	
	private User createUserAccount(UserFormObj ufo, BindingResult result) {
	    User registered = null;
	    try {
	        registered = uService.registerNewUserAccount(ufo);
	    } catch (EntityExistsException e) {
	        return null;
	    }    
	    return registered;
	}
	
	@PostMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable("id") Long id) {
		Optional<User> oU = uService.findUserById(id);
		if (oU.isPresent()) {
			// TODO confirm delete
			uService.deleteUser(oU.get());
		}
		return "index.jsp";
	}
	
	@PostMapping("/signin")
	public String signin(HttpServletRequest request, Model model, @Valid @ModelAttribute("lfo") LoginFormObject lfo,  BindingResult result) {
        if (!result.hasErrors()) {
			Optional<User> oU = uService.findUserByEmail(lfo.getEmail());
			if (oU.isPresent()) {
				User u = (User) oU.get();
				if (u.auth(lfo.getPassword())) {
					uSession = new UserSession(u, Date.from(Instant.now()));
					request.getSession().setAttribute("user-session", uSession);
					DashModel dm = new DashModel();
					dm.user = u;
					List<Account> aa = (List<Account>)u.getAccounts();
					if (!aa.isEmpty())
						dm.account = ((List<Account>)u.getAccounts()).iterator().next();				
					model.addAttribute("dm", dm);
					return "redirect:/dashboard";
				}			
			}
        }
    	lfo.setFailed(true);
    	model.addAttribute("lfo", lfo);
		return "index.jsp";
	}
	
	@GetMapping("/user-test")
	public String user_test(Model model) {
		model.addAttribute("users", uService.allUsers());
		return "user-test.jsp";
	}
}