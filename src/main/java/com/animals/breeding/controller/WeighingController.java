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
import com.animals.breeding.model.Weighing;
import com.animals.breeding.service.WeighingService;

@RestController
public class WeighingController {
	
	@Autowired
	private WeighingService weighingService;
	
	/**
	 * Create - Add a new weighing
	 * @param weighing An object Weighing
	 * @return The new weighing saved
	 */
	@CrossOrigin
	@PostMapping("/weighing")
	public Weighing createWeighing(@RequestBody Weighing weighing) {
		return weighingService.addWeighing(weighing);
	}
	
	/**
	 * Read - Get weighings by animalId
	 * @return - An Iterable object of Weighings
	 */
	@CrossOrigin
	@GetMapping("/weighings/{animalId}")
	public Iterable<Weighing> getWeighingsByAnimal(@PathVariable("animalId") final int animalId) {
		return weighingService.getWeighingsByAnimal(animalId);
	}
	
	/**
	 * Read - Get one weighing
	 * @return - A Weighings object
	 */
	@CrossOrigin
	@GetMapping("/weighing/{id}")/////////////
	public Weighing getWeighing(@PathVariable("id") final int id) {
		Optional <Weighing> weighing = weighingService.getWeighingById(id);
		if (weighing.isPresent()) {
			return weighing.get();
		} else {
			return null;
		}
	}
	
	/**
	 * Read - Get one weighing
	 * @return - A Weighings object
	 */
	@CrossOrigin
	@GetMapping("/latest_weighing/{animalId}")//////////////
	public Weighing getLatestWeighing(@PathVariable("animalId") final int animalId) {
		Weighing lastestWeighing = weighingService.getLatestWeighByAnimal(animalId);
		return lastestWeighing;
	}
	
	/**
	 * Read - Get weighings by animalId
	 * @return - An Iterable object of Weighings
	 */
	@GetMapping("/weighings")
	public Iterable<Weighing> getWeighings() {
		return weighingService.getWeighings();
	}
	
	/**
	 * Update - Update an existing weighing
	 * @param id - The id of the weighing
	 * @param weighing - The weighing object updated
	 * @return
	 */
	@CrossOrigin
	@PutMapping("/weighing/{id}")
	public Weighing updateWeighing(@PathVariable("id") final int id, @RequestBody Weighing weighing) {
		Optional<Weighing> optWeighing = weighingService.getWeighingById(id);
		if (optWeighing.isPresent()) {
			Weighing currentWeighing = optWeighing.get();
			
			int weighValue = weighing.getWeighValue();
			if (weighValue != 0) {
				currentWeighing.setWeighValue(weighValue);
			}
						
			Date weighingDate = weighing.getWeighingDate();
			if (weighingDate != null) {
				currentWeighing.setWeighingDate(weighingDate);
			}
			
			Animal animal = weighing.getAnimal();
			if (animal != null) {
				currentWeighing.setAnimal(animal);
			}
			
			weighingService.saveWeighing(currentWeighing);
					
			return currentWeighing;
		} else {
			return null;
		}
	}
	
	/**
	 * Delete - Delete a weighing
	 * @param id - The id of the weighing to delete
	 */
	@CrossOrigin
	@DeleteMapping("/delete_weighing/{weighingId}")
	public void deleteWeighing(@PathVariable("weighingId") final int weighingId) {
		weighingService.deleteWeighing(weighingId);
	}

}
