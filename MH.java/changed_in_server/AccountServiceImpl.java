package com.btrade.server.service;

import com.btrade.server.model.Account;
import com.btrade.server.model.AccountType;
import com.btrade.server.repository.AccountRepository;
import com.btrade.server.repository.AccountTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

	private final AccountRepository accountRepository;
	private final AccountTypeRepository accountTypeRepository;

	@Autowired
	public AccountServiceImpl(AccountRepository accountRepository, AccountTypeRepository accountTypeRepository) {
		this.accountRepository = accountRepository;
		this.accountTypeRepository = accountTypeRepository;
	}

	@Override
	public Collection<Account> accounts() {
		return accountRepository.findAll().stream().collect(Collectors.toList());
	}

	@Override
	public Account getAccount(long id) {
		Account account = accountRepository.getOne(id);
		System.out.println("AcctNum=" + account.getAcct_num());
		return account;
	}

	@Override
	public void delete(Long id) {
		accountRepository.deleteById(id);
	}

	@Override
	public void save(Account account) {
		accountRepository.save(account);
	}

	@Override
	public Collection<String> getAllAccountTypes() {
		return accountTypeRepository.getAccountTypes();
	}

	@Override
	public Collection<Account> getAccountsByUserId(int uid_no) {
		return accountRepository.getAccountByUserId(uid_no);
	}
	@Override
	public Collection<Account> getAccountsByAcctType(String acct_type) {
		// Assuming that the AccountTypeRepository can fetch AccountType by acct_type (String)
		AccountType accountType = accountTypeRepository.findByAcctType(acct_type);
		if (accountType != null) {
			// Using AccountType id (Long) to fetch the accounts
			return accountRepository.findByAcctTid(accountType.getId());
		} else {
			return new ArrayList<>(); // Return an empty list if no AccountType is found
		}
	}

}
