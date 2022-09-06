package com.spyglass.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spyglass.models.Goal;
import com.spyglass.services.GoalService;

@RestController
@CrossOrigin("*")
@RequestMapping("/goal")
public class GoalController {
	
	@Autowired
	private GoalService service;
	
	@GetMapping
	public List<Goal> findAll(@RequestParam(name="id") int id) {
		return service.findAll(id);
	}
	
	@GetMapping("/{name}")
	public Goal findByName(@PathVariable String name) {
		return service.findByName(name);
	}
	
	@PostMapping
	public ResponseEntity<Goal> save(@RequestBody Goal goal) {
		service.save(goal);
		return new ResponseEntity<>(goal, HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public Goal update(@RequestBody Goal goal, @PathVariable int id) {
		goal.setId(id);
		return service.save(goal);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable int id) {
		service.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
