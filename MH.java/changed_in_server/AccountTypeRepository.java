package com.btrade.server.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.data.repository.query.Param;

import com.btrade.server.model.Account;
import com.btrade.server.model.AccountType;
@Component
public interface AccountTypeRepository extends JpaRepository<AccountType, Long> {

	@Query("SELECT DISTINCT(t.type) FROM AccountType t")
	public Collection<String> getAccountTypes();
	AccountType findById(int id);
	@Query("SELECT a FROM AccountType a WHERE a.type = :type")
	AccountType findByAcctType(@Param("type") String type);
}