package com.btrade.server.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import com.btrade.server.repository.AccountRepository;
import com.btrade.server.repository.UserRepository;
import com.btrade.server.model.Account;
import com.btrade.server.service.AccountService;
import com.btrade.server.service.AccountServiceImpl;
import javax.servlet.http.HttpServletRequest;
import com.btrade.server.model.User;
import com.btrade.server.security.jwt.JwtProvider;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
class AccountController {

	@Autowired
	private AccountService accountService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private JwtProvider jwtProvider;
	@Autowired
	private AccountRepository accountRepository;
	@Autowired(required = true)
	public AccountController(AccountServiceImpl accountService) {
		this.accountService = accountService;
	}

	@GetMapping("/accounts")
	public Collection<Account> getAccounts(HttpServletRequest request) {
		// 从请求头中获取认证令牌
		String token = request.getHeader("Authorization").substring(7);
		// 使用jwtProvider解析令牌以获取用户名
		String username = jwtProvider.getUserNameFromJwtToken(token);
		// 使用用户名从数据库中获取用户
		User user = userRepository.findByUsername(username).orElseThrow(
				() -> new UsernameNotFoundException("User Not Found with -> username: " + username)
		);
		// 获取用户的账户列表
		Collection<Account> accounts = accountRepository.getAccountByUserId(user.getId().intValue());
		return accounts;
	}


	@GetMapping("/accounts/{id}")
	public Account getAccount(@PathVariable long id) {
		System.out.println("id=" + id);
		Account account = accountService.getAccount(id);
		System.out.println("account Balance=" + account.getAvail_balance());
		return account;
	}

	@GetMapping("/accounts/user/{uid_no}")
	public Collection<Account> getAccountsByUserId(@PathVariable int uid_no) {
		System.out.println("id=" + uid_no);
		Collection<Account> accounts = accountService.getAccountsByUserId(uid_no);
		for(Account account : accounts) {
			String accountTypeDescription = account.getAccountType().getDescription();
			System.out.println(account.getAcct_num() + " Account type description: " + accountTypeDescription);
		}
		return accounts;
	}

	@PutMapping("/accounts")
	public void save(@RequestBody Account account) {
		accountService.save(account);
	}

	@PostMapping("/accounts")
	public void update(@RequestBody Account account) {
		accountService.save(account);
	}

	@DeleteMapping("/accounts")
	public void delete(@PathVariable long id) {
		accountService.delete(id);
	}
	@GetMapping("/accounts/types")
	public Collection<String> getAccountTypes() {
		return accountService.getAllAccountTypes();
	}


	@GetMapping("/accounts/type/{acct_type}")
	public Collection<Account> getAccountsByAcctType(@PathVariable("acct_type") String acctType) {
		return accountService.getAccountsByAcctType(acctType);
	}

}