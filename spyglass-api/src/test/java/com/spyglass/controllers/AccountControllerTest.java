package com.spyglass.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.spyglass.services.AccountService;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("dev")
public class AccountControllerTest {

	@MockBean
	AccountService service;
	
	@Autowired
    private MockMvc mockMvc;
	
//	@Test
	@DisplayName("GET /user/test@gmail.com")
	void findUser() throws Exception {
		mockMvc.perform(get("/user/test@gmail.com").with(SecurityMockMvcRequestPostProcessors.jwt()))
			.andExpect(status().isOk());
	}
}