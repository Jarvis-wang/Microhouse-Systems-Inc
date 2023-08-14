package com.btrade.server.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import com.btrade.server.model.AccountType;
import java.util.List;
import com.btrade.server.model.Account;

@Component
public interface AccountRepository extends JpaRepository<Account, Long> {

	@Query("SELECT t FROM Account t where t.acct_num = :acct_num")
	public Account getAvailableBalance(@Param("acct_num") int acct_num);

	@Query("SELECT t FROM Account t where t.uid_no = :uid_no")
	public Collection<Account> getAccountByUserId(@Param("uid_no") int uid_no);

	@Query("SELECT a FROM Account a WHERE a.accountType.id = :acct_tid")
	Collection<Account> findByAcctTid(@Param("acct_tid") Long acct_tid);



	@Query("SELECT t FROM Account t WHERE t.sub_type = :sub_type")
	public Collection<Account> findBySubType(@Param("sub_type") String subType);




}