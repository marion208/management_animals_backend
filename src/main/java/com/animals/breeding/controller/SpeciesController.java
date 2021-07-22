package com.animals.breeding.controller;

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

import com.animals.breeding.model.Species;
import com.animals.breeding.service.SpeciesService;

@RestController
public class SpeciesController {
	
	@Autowired
	private SpeciesService speciesService;
	
	/**
	 * Create - Add a new species
	 * @param species An object Species
	 * @return The new species saved
	 */
	@CrossOrigin
	@PostMapping("/newspecies")
	public Species createSpecies(@RequestBody Species species) {
		return speciesService.addOneSpecies(species);
	}
	
	/**
	 * Read - Get all species
	 * @return - An Iterable object of Species
	 */
	@CrossOrigin
	@GetMapping("/species")
	public Iterable<Species> getSpecies() {
		return speciesService.getSpecies();
	}
	
	/**
	 * Read - Get one species
	 * @return - A Species object
	 */
	@CrossOrigin
	@GetMapping("/species/{id}")
	public Species getOneSpecies(@PathVariable("id") final int id) {
		Optional <Species> species = speciesService.getSpeciesById(id);
		if (species.isPresent()) {
			return species.get();
		} else {
			return null;
		}
	}
	
	/**
	 * Update - Update an existing species
	 * @param id - The id of the species
	 * @param species - The species object updated
	 * @return
	 */
	@CrossOrigin
	@PutMapping("/species/{id}")
	public Species updateSpecies(@PathVariable("id") final int id, @RequestBody Species species) {
		Optional<Species> optSpecies = speciesService.getSpeciesById(id);
		if (optSpecies.isPresent()) {
			Species currentSpecies = optSpecies.get();
			
			String designation = species.getDesignation();
			if(designation != null) {
				currentSpecies.setDesignation(designation);
			}
			
			speciesService.saveSpecies(currentSpecies);

			return currentSpecies;
		} else {
			return null;
		}
	}
	
	/**
	 * Delete - Delete a species
	 * @param id - The id of the species to delete
	 */
	@CrossOrigin
	@DeleteMapping("/delete_species/{speciesId}")
	public void deleteSpecies(@PathVariable("speciesId") final int speciesId) {
		speciesService.deleteSpecies(speciesId);
	}

}
