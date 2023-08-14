package com.btrade.server.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "ACCOUNT")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Account {

	@Id
	@Column(name = "ACCT_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long acct_Id;

	@ManyToOne
	@JoinColumn(name="ACCT_TID", referencedColumnName="ID",nullable=false)
	private AccountType accountType;

	@Column(name = "ACCT_NUM", nullable = false)
	private int acct_num;

	@Column(name = "SUB_TYPE", nullable = false)
	private String sub_type;

	@Column(name = "BALANCE", nullable = false)
	private String balance;

	@Column(name = "AVAIL_BALANCE", nullable = false)
	private String avail_balance;

	@Column(name = "UID_NO", nullable = false)
	private int uid_no;

	public Account() {
	}
	public AccountType getAccountType() {
		return accountType;
	}

	public Long getAcct_Id() {
		return acct_Id;
	}

	public void setAcct_Id(Long acct_Id) {
		this.acct_Id = acct_Id;
	}

	public int getAcct_num() {
		return acct_num;
	}

	public void setAcct_num(int acct_num) {
		this.acct_num = acct_num;
	}

	public String getSub_type() {
		return sub_type;
	}

	public void setSub_type(String sub_type) {
		this.sub_type = sub_type;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getAvail_balance() {
		return avail_balance;
	}

	public void setAvail_balance(String avail_balance) {
		this.avail_balance = avail_balance;
	}

	public int getUid_no() {
		return uid_no;
	}

	public void setUid_no(int uid_no) {
		this.uid_no = uid_no;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}


	public String getAcct_type() {
		if (this.accountType != null) {
			return this.accountType.getType();
		} else {
			return null;
		}
	}


	public Account(int acct_num, AccountType accountType, String sub_type, String balance, String avail_balance,
				   int uid_no) {
		this.acct_num = acct_num;
		this.accountType = accountType;
		this.sub_type = sub_type;
		this.balance = balance;
		this.avail_balance = avail_balance;
		this.uid_no = uid_no;
	}

	@Override
	public String toString() {
		return "Account [acct_Id=" + acct_Id + ", acct_num=" + acct_num + ", accountType=" + (accountType != null ? accountType.getType() : "null") + ", sub_type="
				+ sub_type + ", balance=" + balance + ", avail_balance=" + avail_balance + ", uid_no=" + uid_no
				+ "]";
	}

}
