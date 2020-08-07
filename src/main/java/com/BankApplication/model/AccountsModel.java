package com.BankApplication.model;

public class AccountsModel {
	// Declarations
	String Txn_ref_number;
	String Account_number;
	String Date_;
	String Time_;
	String Desciption_;
	String Withdrawals;
	String Credit;
	String Running_Balance;

	// Creating getter and setter methods
	public String getTxn_ref_number() {
		return Txn_ref_number;
	}

	public void setTxn_ref_number(String txn_ref_number) {
		Txn_ref_number = txn_ref_number;
	}

	public String getAccount_number() {
		return Account_number;
	}

	public void setAccount_number(String account_number) {
		Account_number = account_number;
	}

	public String getDate_() {
		return Date_;
	}

	public void setDate_(String date_) {
		Date_ = date_;
	}

	public String getTime_() {
		return Time_;
	}

	public void setTime_(String time_) {
		Time_ = time_;
	}

	public String getDesciption_() {
		return Desciption_;
	}

	public void setDesciption_(String desciption_) {
		Desciption_ = desciption_;
	}

	public String getWithdrawals() {
		return Withdrawals;
	}

	public void setWithdrawals(String withdrawals) {
		Withdrawals = withdrawals;
	}

	public String getCredit() {
		return Credit;
	}

	public void setCredit(String credit) {
		Credit = credit;
	}

	public String getRunning_Balance() {
		return Running_Balance;
	}

	public void setRunning_Balance(String running_Balance) {
		Running_Balance = running_Balance;
	}

}
