package com.spyglass.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spyglass.models.Goal;
import com.spyglass.repositories.GoalRepo;

@Service @Transactional
public class GoalServiceImpl implements GoalService {

	@Autowired
	private GoalRepo repository;
	
	@Override
	public List<Goal> findAll(int id) {
		return repository.findAll(id);
	}

	@Override
	public Goal findByName(String name) {
		Optional<Goal> goal = Optional.of(repository.findByName(name));
		return goal.isPresent() ? goal.get() : null;
	}
	
	@Override
	public Goal save(Goal goal) {
		return repository.save(goal);
	}

	@Override
	public Goal update(Goal goal) {
		return repository.save(goal);
	}

	@Override
	public void delete(int id) {
		repository.deleteById(id);;
	}

}
