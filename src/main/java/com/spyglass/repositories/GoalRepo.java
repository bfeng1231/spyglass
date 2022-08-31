package com.spyglass.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spyglass.models.Goal;

public interface GoalRepo extends JpaRepository<Goal, Integer> {
	@Query("from Goal where account_id = :id ")
	List<Goal> findAll(@Param("id")int id);
	
	Goal findByName(String name);
}
