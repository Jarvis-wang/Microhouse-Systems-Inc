package com.btrade.server.service;

import java.util.Collection;

import com.btrade.server.model.Account;
import com.btrade.server.repository.AccountRepository;
public interface AccountService{


	public Collection<Account> accounts();
	
	public Collection<Account> getAccountsByUserId(int uid_no);

	public Collection<String> getAllAccountTypes();
	public Collection<Account> getAccountsByAcctType(String acct_type);
	public Account getAccount(long id);

	public void delete(Long id);

	public void save(Account account);

}