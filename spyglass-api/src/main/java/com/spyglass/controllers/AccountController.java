package com.spyglass.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spyglass.models.Account;
import com.spyglass.services.AccountService;

@RestController
@RequestMapping("/user")
public class AccountController {

	@Autowired
	private AccountService service;
	
	@GetMapping("/{email}")
	public Account findByEmail(@PathVariable String email) {
		return service.findByEmail(email);
	}
	
	@PostMapping("/register")
	public ResponseEntity<Account> save(@RequestBody Account account) {
		try {
			service.save(account);
			return new ResponseEntity<>(account, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}		
	}
	
	@PutMapping("/{id}")
	public Account update(@RequestBody Account account, @PathVariable int id) {
		account.setId(id);
		return service.save(account);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable int id) {
		service.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
