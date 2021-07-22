package com.animals.breeding.controller;

import java.sql.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.animals.breeding.model.Animal;
import com.animals.breeding.model.Gender;
import com.animals.breeding.model.Species;
import com.animals.breeding.service.AnimalService;

@RestController
public class AnimalController {
	
	@Autowired
	private AnimalService animalService;
	
	/**
	 * Create - Add a new animal
	 * @param animal An object Animal
	 * @return The new animal saved
	 */
	@CrossOrigin
	@PostMapping("/animal")
	public Animal createAnimal(@RequestBody Animal animal) {
		return animalService.addAnimal(animal);
	}
	
	/**
	 * Read - Get all animals
	 * @return - An Iterable object of Animals
	 */
	@CrossOrigin
	@GetMapping("/animals")
	public Iterable<Animal> getAnimals() {
		return animalService.getAnimals();
	}
	
	/**
	 * Read - Get one animal
	 * @return - An Animal object
	 */
	@CrossOrigin
	@GetMapping("/animal/{id}")
	public Animal getAnimal(@PathVariable("id") final int id) {
		Optional <Animal> animal = animalService.getAnimalById(id);
		if (animal.isPresent()) {
			return animal.get();
		} else {
			return null;
		}
	}
	
	/**
	 * Update - Update an existing animal
	 * @param id - The id of the animal
	 * @param animal - The animal object updated
	 * @return
	 */
	@CrossOrigin
	@PutMapping("/animal/{id}")
	public Animal updateAnimal(@PathVariable("id") final int id, @RequestBody Animal animal) {
		Optional<Animal> optAnimal = animalService.getAnimalById(id);
		if (optAnimal.isPresent()) {
			Animal currentAnimal = optAnimal.get();
			
			String name = animal.getName();
			if(name != null) {
				currentAnimal.setName(name);
			}
			
			int identificationNumber = animal.getIdentificationNumber();
			if (identificationNumber != 0) {
				currentAnimal.setIdentificationNumber(identificationNumber);
			}
			
			Date birthDate = animal.getBirthDate();
			if (birthDate != null) {
				currentAnimal.setBirthDate(birthDate);
			}
			
			String notes = animal.getNotes();
			if (notes != null) {
				currentAnimal.setNotes(notes);
			}
			
			Gender gender = animal.getGender();
			if (gender != null) {
				currentAnimal.setGender(gender);
			}
			
			Species species = animal.getSpecies();
			if (species != null) {
				currentAnimal.setSpecies(species);
			}
			
			animalService.saveAnimal(currentAnimal);
			
			return currentAnimal;
		} else {
			return null;
		}
	}
	
	/**
	 * Delete - Delete an animal
	 * @param id - The id of the animal to delete
	 */
	@CrossOrigin
	@DeleteMapping("/delete_animal/{animalId}")
	public void deleteAnimal(@PathVariable("animalId") final int animalId) {
		animalService.deleteAnimal(animalId);
	}

}
