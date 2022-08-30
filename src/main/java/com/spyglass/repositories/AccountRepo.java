package com.spyglass.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spyglass.models.Account;

public interface AccountRepo extends JpaRepository<Account, Integer> {
	Account findByEmail(String email);
}
