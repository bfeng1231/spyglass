package com.spyglass.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.spyglass.services.GoalService;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("dev")
public class GoalControllerTest {

	@MockBean
	GoalService service;
	
	@Autowired
    private MockMvc mockMvc;
	
//	@Test
	void findAllGoals() throws Exception {
		mockMvc.perform(get("/goal?id=5").with(SecurityMockMvcRequestPostProcessors.jwt()))
				.andExpect(status().isOk());
	}
}
