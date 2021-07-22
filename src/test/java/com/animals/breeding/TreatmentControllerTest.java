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
import com.animals.breeding.model.Treatment;
import com.animals.breeding.service.AnimalService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class TreatmentControllerTest {
	
	@Autowired
	public MockMvc mockMvc;
	
	@Autowired
	private AnimalService animalService;
	
	@Test
	public void testGetTreatments() throws Exception {
		mockMvc.perform(get("/treatments"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].description", is("Vermifuge")))
		.andExpect(jsonPath("$[0].notes", is("1 comprimé")))
		.andExpect(jsonPath("$[0].dateTreatment", is("2021-07-14")))
		.andExpect(jsonPath("$[0].dateRenewal", is("2022-07-14")));
	}
	
	@Test
	public void testGetTreatmentsByAnimalId() throws Exception {
		mockMvc.perform(get("/treatments/1"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].description", is("Vermifuge")))
		.andExpect(jsonPath("$[0].notes", is("1 comprimé")))
		.andExpect(jsonPath("$[0].dateTreatment", is("2021-07-14")))
		.andExpect(jsonPath("$[0].dateRenewal", is("2022-07-14")));
	}
	
	@Test
	public void testGetRenewalsByAnimalId() throws Exception {
		mockMvc.perform(get("/renewals/1"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[1].description", is("Vermifuge")))
		.andExpect(jsonPath("$[1].notes", is("1 comprimé")))
		.andExpect(jsonPath("$[1].dateTreatment", is("2021-07-14")))
		.andExpect(jsonPath("$[1].dateRenewal", is("2022-07-14")));
	}
	
	@Test
	public void testAddTreatment() throws Exception {
		Treatment newTreatment = new Treatment();
		
		Optional<Animal> optWeighingAnimal = animalService.getAnimalById(1);
		Animal weighingAnimal = optWeighingAnimal.get();
		
		newTreatment.setAnimal(weighingAnimal);
		newTreatment.setDescription("Vaccin bovin");
		
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		Date treatmentDate = new Date(df.parse("18-07-2021").getTime());
		newTreatment.setDateTreatment(treatmentDate);
		
		Date renewalDate = new Date(df.parse("18-07-2022").getTime());
		newTreatment.setDateRenewal(renewalDate);
		
		mockMvc.perform(post("/treatment")
				.content(asJsonString(newTreatment))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.description", is("Vaccin bovin")))
				.andExpect(jsonPath("$.notes", is(IsNull.nullValue())))
				.andExpect(jsonPath("$.dateTreatment", is("2021-07-18")))
				.andExpect(jsonPath("$.dateRenewal", is("2022-07-18")));
	}
	
	@Test
	public void testUpdateTreatment() throws Exception {
		Treatment updatedTreatment = new Treatment();
		updatedTreatment.setTreatmentId(5);
		updatedTreatment.setDescription("Vaccin");
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		Date treatmentDate = new Date(df.parse("18-07-2021").getTime());
		updatedTreatment.setDateTreatment(treatmentDate);
		Date renewalDate = new Date(df.parse("18-07-2022").getTime());
		updatedTreatment.setDateRenewal(renewalDate);
		
		Optional<Animal> optAnimal= animalService.getAnimalById(1);
		Animal animal = optAnimal.get();
		updatedTreatment.setAnimal(null);
		
		mockMvc.perform(put("/treatment/5")
				.content(asJsonString(updatedTreatment))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.description", is("Vaccin")))
				.andExpect(jsonPath("$.dateTreatment", is("2021-07-18")))
				.andExpect(jsonPath("$.dateRenewal", is("2022-07-18")));
	}
	
	@Test
	public void testGetTreatmentById() throws Exception {
		mockMvc.perform(get("/treatment/1"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.description", is("Vermifuge")))
			.andExpect(jsonPath("$.notes", is("1 comprimé")))
			.andExpect(jsonPath("$.dateTreatment", is("2021-07-14")))
			.andExpect(jsonPath("$.dateRenewal", is("2022-07-14")));
	}
	
	@Test
	public void testDeleteTreatment() throws Exception {
		mockMvc.perform(delete("/delete_treatment/4")
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
