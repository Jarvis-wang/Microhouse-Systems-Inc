package com.btrade.server.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.btrade.server.model.AccountType;
import com.btrade.server.model.GeneralException;
import com.btrade.server.service.AccountTypeService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/accounttype")
public class AccountTypeController {
	@Autowired
	private AccountTypeService accountTypeService;
	/*
	 * Save accountTypeService
	 */

	@RequestMapping(value = "/{acct_type}/{description}", method = RequestMethod.POST, headers = "Accept=application/json")
	public void saveAccountType(@PathVariable String acct_type, @PathVariable String description) {

		AccountType accountType = new AccountType(acct_type, description);
		try {
			// accountTypeService.saveAccountType(accountType);
		} catch (Exception e) {
			throw new GeneralException("The account type " + accountType + " already exists");
		}
	}

	/*
	 * Query account type list and return it to front end as json
	 */

	@RequestMapping(value = "/accounttypes", method = RequestMethod.GET, headers = "Accept=application/json")
	public Collection<String> listAccountType() {
		Collection<String> list = accountTypeService.accountTypes();
		return list;
	}

	/*
	 * Query account type with the specified acct_type
	 */

	@RequestMapping(value = "/{acct_type}", method = RequestMethod.GET, headers = "Accept=application/json")
	AccountType getAccountTypeById(@PathVariable String acct_type) {
		try {
			AccountType accountType = null;
			// accountType= accountTypeService.getAccountTypeById(acct_type);
			return accountType;
		} catch (Exception e) {
			throw new GeneralException("The acct_type " + acct_type + " doesn't exist.");
		}
	}

	/*
	 * Delete account type with the specified acct_type
	 */

	@RequestMapping(value = "/{acct_type}/delete", method = RequestMethod.GET, headers = "Accept=application/json")
	public boolean deleteAccountTypeById(@PathVariable String acct_type) {
		// boolean result = accountTypeService.deleteAccountTypeById(acct_type);
		boolean result = false;
		if (result) {
			return true;
		} else {
			throw new GeneralException("The acct_type " + acct_type + " profile doesn't exist");
		}
	}

}
