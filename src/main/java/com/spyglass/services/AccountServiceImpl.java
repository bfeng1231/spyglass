package com.spyglass.services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.spyglass.models.Account;
import com.spyglass.repositories.AccountRepo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service @Transactional
public class AccountServiceImpl implements AccountService, UserDetailsService {

	private static final Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);
	
	@Autowired
	private AccountRepo repository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Account account = repository.findByEmail(email);
		if (account == null) {
			log.error("Account not found");
			throw new UsernameNotFoundException("Account not found");
		} else {
			log.info("Found account: {}", account.toString());
		}
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("User"));
		
		return new org.springframework.security.core.userdetails.User(account.getEmail(), 
				account.getPassword(), authorities);
	}
	
	@Override
	public Account findByEmail(String email) {
		log.trace("Looking for email: {}", email);
		return repository.findByEmail(email);
		//return account.isPresent() ? account.get() : null;
	}

	@Override
	public Account save(Account account) {
		log.trace("Registering new user {}", account.toString());
		account.setPassword(encoder.encode(account.getPassword()));
		return repository.save(account);
	}
	

	@Override
	public Account update(Account account) {
		log.trace("Updating user info");
		return repository.save(account);
	}

	@Override
	public void delete(int id) {
		log.trace("Deleting user {}", id);
		repository.deleteById(id);
	}


}
