package com.animals.breeding;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.animals.breeding.model.Species;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class SpeciesControllerTest {
	
	@Autowired
	public MockMvc mockMvc;
	
	@Test
	public void testGetSpecies() throws Exception {
		mockMvc.perform(get("/species"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[1].speciesId", is(1)))
		.andExpect(jsonPath("$[1].designation", is("Bovin")));
	}
	
	@Test
	public void testAddOneSpecies() throws Exception {
		Species newSpecies = new Species();
		newSpecies.setDesignation("Cochon");
		
		mockMvc.perform(post("/newspecies")
			.content(asJsonString(newSpecies))
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.speciesId", is(IsNull.notNullValue())))
			.andExpect(jsonPath("$.designation", is("Cochon")));
	}
	
	@Test
	public void testUpdateSpecies() throws Exception {
		Species updatedSpecies = new Species();
		updatedSpecies.setSpeciesId(1);
		updatedSpecies.setDesignation("Bovin");
	
		mockMvc.perform(put("/species/1")
				.content(asJsonString(updatedSpecies))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.speciesId", is(1)))
				.andExpect(jsonPath("$.designation", is("Bovin")));
	}
	
	@Test
	public void testGetOneSpecies() throws Exception {
		mockMvc.perform(get("/species/1"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.speciesId", is(1)))
			.andExpect(jsonPath("$.designation", is("Bovin")));
	}
	
	@Test
	public void testDeleteOneSpecies() throws Exception {
		mockMvc.perform(delete("/delete_species/5")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}

}
