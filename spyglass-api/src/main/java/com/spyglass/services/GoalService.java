package com.spyglass.services;

import java.util.List;

import com.spyglass.models.Goal;

public interface GoalService {
	public List<Goal> findAll(int id);
	public Goal findByName(String name);
	public Goal save(Goal goal);
	public Goal update(Goal goal);
	public void delete(int id);
}
