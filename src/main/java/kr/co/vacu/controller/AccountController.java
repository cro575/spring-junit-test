package kr.co.vacu.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import kr.co.vacu.domain.Account;
import kr.co.vacu.service.AccountManager;
import kr.co.vacu.service.MockAccountManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles requests for the application home page.
 */
@Controller
public class AccountController {
	
	private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
	
	AccountManager accountManager = new MockAccountManager();

	public void setAccountManager(AccountManager accountManager){
		this.accountManager = accountManager;
	}
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/account/list", method = RequestMethod.GET)
	public String list(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response/*, @RequestParam String keyword*/) {
		logger.info("Welcome home! The client locale is {}.", locale);

		String keyword = request.getParameter("keyword");
		
		accountManager.addAccount("1", new Account("1", 200));
		accountManager.addAccount("2", new Account("2", 100));

		
		model.addAttribute("keyword", keyword);
		model.addAttribute("list", accountManager.listAllValuse());
		
		return "account/list";
	}
	
	@RequestMapping(value = "/account/new", method = RequestMethod.POST)
	public String formSubmit(@Valid Account account, BindingResult result, Model model) {
		if( result.hasErrors()) {
			return "account/form";
		}
		
		accountManager.addAccount("1", account);
		
		return "redirect:account/list";
	}	
}
