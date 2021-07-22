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
import com.animals.breeding.model.Treatment;
import com.animals.breeding.service.TreatmentService;

@RestController
public class TreatmentController {
	
	@Autowired
	private TreatmentService treatmentService;
	
	/**
	 * Create - Add a new treatment
	 * @param treatment An object Treatment
	 * @return The new treatment saved
	 */
	@CrossOrigin
	@PostMapping("/treatment")
	public Treatment addTreatment(@RequestBody Treatment treatment) {
		return treatmentService.addTreatment(treatment);
	}
	
	/**
	 * Read - Get treatments by animalId
	 * @return - An Iterable object of Treatments
	 */
	@CrossOrigin
	@GetMapping("/treatments/{animalId}")
	public Iterable<Treatment> getTreatmentsByAnimal(@PathVariable("animalId") final int animalId) {
		return treatmentService.getTreatmentsByAnimal(animalId);
	}
	
	/**
	 * Read - Get renewals by animalId
	 * @return - An Iterable object of Treatments
	 */
	@GetMapping("/renewals/{animalId}")
	public Iterable<Treatment> getRenewalsByAnimalId(@PathVariable("animalId") final String animalId) {
		return treatmentService.getRenewalTreatmentsByAnimal(Integer.parseInt(animalId));
	}
	
	/**
	 * Read - Get treatments by animalId
	 * @return - An Iterable object of Treatments
	 */
	@GetMapping("/treatments")
	public Iterable<Treatment> getTreatments() {
		return treatmentService.getTreatments();
	}
	
	/**
	 * Read - Get one treatment
	 * @return - A Treatment object
	 */
	@CrossOrigin
	@GetMapping("/treatment/{id}")
	public Treatment getTreatment(@PathVariable("id") final int id) {
		Optional <Treatment> treatment = treatmentService.getTreatmentById(id);
		if (treatment.isPresent()) {
			return treatment.get();
		} else {
			return null;
		}
	}
	
	/**
	 * Update - Update an existing treatment
	 * @param id - The id of the treatment
	 * @param treatment - The treatment object updated
	 * @return
	 */
	@CrossOrigin
	@PutMapping("/treatment/{id}")
	public Treatment updateTreatment(@PathVariable("id") final int id, @RequestBody Treatment treatment) {
		Optional<Treatment> optTreatment = treatmentService.getTreatmentById(id);
		if (optTreatment.isPresent()) {
			Treatment currentTreatment = optTreatment.get();
			
			String description = treatment.getDescription();
			if (description != null) {
				currentTreatment.setDescription(description);
			}
			
			String notes = treatment.getNotes();
			if (notes != null) {
				currentTreatment.setNotes(notes);
			}
			
			Date treatmentDate = treatment.getDateTreatment();
			if (treatmentDate != null) {
				currentTreatment.setDateTreatment(treatmentDate);
			}
			
			Date renewalDate = treatment.getDateRenewal();
			if (renewalDate != null) {
				currentTreatment.setDateRenewal(renewalDate);
			}
			
			Animal animal = treatment.getAnimal();
			if (animal != null) {
				currentTreatment.setAnimal(animal);
			}
			
			treatmentService.saveTreatment(currentTreatment);
					
			return currentTreatment;
		} else {
			return null;
		}
	}
	
	/**
	 * Delete - Delete a treatment
	 * @param id - The id of the treatment to delete
	 */
	@CrossOrigin
	@DeleteMapping("/delete_treatment/{treatmentId}")
	public void deleteTreatment(@PathVariable("treatmentId") final int treatmentId) {
		treatmentService.deleteTreatment(treatmentId);
	}

}
