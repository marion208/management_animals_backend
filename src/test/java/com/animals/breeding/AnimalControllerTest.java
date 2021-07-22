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
import com.animals.breeding.model.Gender;
import com.animals.breeding.model.Species;
import com.animals.breeding.service.GenderService;
import com.animals.breeding.service.SpeciesService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class AnimalControllerTest {
	
	@Autowired
	public MockMvc mockMvc;
	
	@Autowired
	private GenderService genderService;
	
	@Autowired
	private SpeciesService speciesService;
	
	@Test
	public void testGetAnimals() throws Exception {
		
		mockMvc.perform(get("/animals"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[3].animalId", is(1)))
		.andExpect(jsonPath("$[3].name", is("Marguerite")))
		.andExpect(jsonPath("$[3].identificationNumber", is(12457)))
		.andExpect(jsonPath("$[3].birthDate", is("2020-10-05")))
		.andExpect(jsonPath("$[3].notes", is("")))
		.andExpect(jsonPath("$[3].gender.genderId", is(2)))
		.andExpect(jsonPath("$[3].gender.description", is("Femelle")))
		.andExpect(jsonPath("$[3].species.speciesId", is(1)))
		.andExpect(jsonPath("$[3].species.designation", is("Bovin")));
	}
	
	@Test
	public void testAddAnimal() throws Exception {
		Animal newAnimal = new Animal();
		newAnimal.setName("Biscotte");
		newAnimal.setIdentificationNumber(52142);
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		Date birthDate = new Date(df.parse("12-01-2021").getTime());
		newAnimal.setBirthDate(birthDate);
		newAnimal.setNotes(null);
		
		Optional<Gender> optAnimalGender = genderService.getGenderById(2);
		Gender animalGender = optAnimalGender.get();
		
		Optional<Species> optAnimalSpecies = speciesService.getSpeciesById(2);
		Species animalSpecies = optAnimalSpecies.get();
				
		newAnimal.setGender(animalGender);
		newAnimal.setSpecies(animalSpecies);
		
		mockMvc.perform(post("/animal")
				.content(asJsonString(newAnimal))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.animalId", is(IsNull.notNullValue())))
				.andExpect(jsonPath("$.name", is("Biscotte")))
				.andExpect(jsonPath("$.identificationNumber", is(52142)))
				.andExpect(jsonPath("$.birthDate", is("2021-01-12")))
				.andExpect(jsonPath("$.notes", is(IsNull.nullValue())))
				.andExpect(jsonPath("$.gender.genderId", is(2)))
				.andExpect(jsonPath("$.species.speciesId", is(2)));
	}
		
	
	
	@Test
	public void testUpdateAnimal() throws Exception {
		
		Animal updatedAnimal = new Animal();
		updatedAnimal.setAnimalId(6);
		updatedAnimal.setName("Babe");
		updatedAnimal.setIdentificationNumber(13957);
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		Date birthDate = new Date(df.parse("12-01-2021").getTime());
		updatedAnimal.setBirthDate(birthDate);
		
		Optional<Gender> optAnimalGender = genderService.getGenderById(1);
		Gender animalGender = optAnimalGender.get();
		
		Optional<Species> optAnimalSpecies = speciesService.getSpeciesById(3);
		Species animalSpecies = optAnimalSpecies.get();
				
		updatedAnimal.setGender(animalGender);
		updatedAnimal.setSpecies(animalSpecies);
		
		mockMvc.perform(put("/animal/6")
				.content(asJsonString(updatedAnimal))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.animalId", is(6)))
				.andExpect(jsonPath("$.name", is("Babe")))
				.andExpect(jsonPath("$.identificationNumber", is(13957)))
				.andExpect(jsonPath("$.birthDate", is("2021-01-12")))
				.andExpect(jsonPath("$.gender.genderId", is(1)))
				.andExpect(jsonPath("$.species.speciesId", is(3)));
	}
	
	@Test
	public void testGetOneAnimal() throws Exception {
		mockMvc.perform(get("/animal/6"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.animalId", is(6)))
				.andExpect(jsonPath("$.name", is("Babe")))
				.andExpect(jsonPath("$.identificationNumber", is(13957)))
				.andExpect(jsonPath("$.birthDate", is("2021-01-12")))
				.andExpect(jsonPath("$.gender.genderId", is(1)))
				.andExpect(jsonPath("$.species.speciesId", is(3)));
	}
	
	@Test
	public void testDeleteAnimal() throws Exception {
		mockMvc.perform(delete("/delete_animal/7")
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
