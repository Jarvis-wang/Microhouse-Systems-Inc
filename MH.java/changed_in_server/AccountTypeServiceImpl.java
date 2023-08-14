package com.btrade.server.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btrade.server.model.AccountType;
import com.btrade.server.repository.AccountTypeRepository;

@Service
public class AccountTypeServiceImpl implements AccountTypeService{
	
	@Autowired
	private AccountTypeRepository accountTypeRepository;
	
	@Override
	public Collection<String> accountTypes() {
		// TODO Auto-generated method stub
		
		Collection<String> accountTypes = accountTypeRepository.getAccountTypes();
		//System.out.println(accountTypes.size());
		return accountTypes;
	}
	
}