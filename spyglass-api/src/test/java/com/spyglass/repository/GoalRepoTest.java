package com.spyglass.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.spyglass.models.Account;
import com.spyglass.models.Goal;
import com.spyglass.repositories.AccountRepo;
import com.spyglass.repositories.GoalRepo;

@DataJpaTest
public class GoalRepoTest {
	
	@Autowired
	private GoalRepo repository;
	
	@Autowired
	private AccountRepo accRepo;
	
	private Account account;
	
	@BeforeClass
	void createUser() {
		account = new Account(
				0, "test@gmail.com",
				"$2y$10$YMfX6N2nmpA5fAy9Ax6fsuEIHOG7qq3Ukoc8MZTyTLxu4TYLK.AVW",
				"Bob", "Rob", "1999-05-04"
				);
		accRepo.save(account);
	}
	
	@Test
	void itShouldGetAllGoals() {
		List<Goal> goals = repository.findAll(5);
		assertNotNull(goals);
	}
	
	@Test
	void itShouldAddAGoal() {
		Goal goal = new Goal(0, "Bike", "", "", "2022-09-19", 100, 0);
		goal.setAccount(account);
		goal = repository.save(goal);
		assertEquals(goal, new Goal(1, "Bike", "", "", "2022-09-19", 100, 0));
	}
	
	@Test
	void itShouldDeleteAGoal() {
		repository.deleteById(1);
		List<Goal> goals = repository.findAll(1);
		System.out.println(goals);
		assertEquals(goals.size(), 1);
	}
}
