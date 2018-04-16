package com.example.service;

import com.example.model.Account;

public interface AccountService {
	public Account findUserByEmail(String email);
	public void saveAccount(Account account);
}
