package com.animals.breeding;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Optional;

import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.animals.breeding.model.Animal;
import com.animals.breeding.model.Weighing;
import com.animals.breeding.service.AnimalService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class WeighingControllerTest {
	
	@Autowired
	public MockMvc mockMvc;
	
	@Autowired
	private AnimalService animalService;
	
	@Test
	public void testGetWeighings() throws Exception {
		mockMvc.perform(get("/weighings"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].weighingId", is(1)))
		.andExpect(jsonPath("$[0].weighValue", is(874)))
		.andExpect(jsonPath("$[0].weighingDate", is("2021-07-16")));
	}
	
	@Test
	public void testGetWeighingsByAnimalId() throws Exception {
		mockMvc.perform(get("/weighings")
		.param("animalId", "1"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].weighingId", is(1)))
		.andExpect(jsonPath("$[0].weighValue", is(874)))
		.andExpect(jsonPath("$[0].weighingDate", is("2021-07-16")));
	}
	
	@Test
	public void testAddWeighing() throws Exception {
		Weighing newWeighing = new Weighing();
		
		Optional<Animal> optWeighingAnimal = animalService.getAnimalById(1);
		Animal weighingAnimal = optWeighingAnimal.get();
		
		newWeighing.setAnimal(weighingAnimal);
		newWeighing.setWeighValue(877);
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		Date weighingDate = new Date(df.parse("18-07-2021").getTime());
		newWeighing.setWeighingDate(weighingDate);
		
		mockMvc.perform(post("/weighing")
				.content(asJsonString(newWeighing))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.weighingId", is(IsNull.notNullValue())))
				.andExpect(jsonPath("$.weighValue", is(877)))
				.andExpect(jsonPath("$.weighingDate", is("2021-07-18")));
	}
	
	@Test
	public void testUpdateWeighing() throws Exception {
		Weighing updatedWeighing = new Weighing();
		updatedWeighing.setWeighingId(3);
		updatedWeighing.setWeighValue(876);
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		Date weighingDate = new Date(df.parse("18-07-2021").getTime());
		updatedWeighing.setWeighingDate(weighingDate);
		
		Optional<Animal> optAnimal= animalService.getAnimalById(1);
		Animal animal = optAnimal.get();
		
		updatedWeighing.setAnimal(animal);
		
		mockMvc.perform(put("/weighing/3")
				.content(asJsonString(updatedWeighing))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.weighingId", is(3)))
				.andExpect(jsonPath("$.weighValue", is(876)))
				.andExpect(jsonPath("$.weighingDate", is("2021-07-18")));
	}
	
	@Test
	public void testGetWeighingById() throws Exception {
		mockMvc.perform(get("/weighing/1"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.weighingId", is(1)))
		.andExpect(jsonPath("$.weighValue", is(874)))
		.andExpect(jsonPath("$.weighingDate", is("2021-07-16")));
	}
	
	@Test
	public void testGestLatestWeighingByAnimal() throws Exception {
		mockMvc.perform(get("/weighing/1"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.weighingId", is(4)))
		.andExpect(jsonPath("$.weighValue", is(878)))
		.andExpect(jsonPath("$.weighingDate", is("2021-07-21")));
	}
	
	@Test
	public void testDeleteWeighing() throws Exception {
		mockMvc.perform(delete("/delete_weighing/2")
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
