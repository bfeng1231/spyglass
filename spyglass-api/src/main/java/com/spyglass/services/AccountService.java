package com.spyglass.services;

import com.spyglass.models.Account;

public interface AccountService {
	public Account findByEmail(String email);
	public Account save(Account account);
	public Account update(Account account);
	public void delete(int id);
}
