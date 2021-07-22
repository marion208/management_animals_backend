package com.animals.breeding;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class GenderControllerTest {
	
	@Autowired
	public MockMvc mockMvc;
	
	@Test
	public void testGetGenders() throws Exception {
		mockMvc.perform(get("/genders"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].genderId", is(1)))
		.andExpect(jsonPath("$[0].description", is("Mâle")));
	}

}
