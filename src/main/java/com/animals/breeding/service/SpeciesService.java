package com.animals.breeding.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.animals.breeding.model.Animal;
import com.animals.breeding.model.Species;
import com.animals.breeding.model.Treatment;
import com.animals.breeding.repository.SpeciesRepository;

@Service
public class SpeciesService {
	
	@Autowired
	private SpeciesRepository speciesRepository;
	
	@Autowired
	private AnimalService animalService;
	
	@Autowired
	private TreatmentService treatmentService;
	
	public Iterable<Species> getSpecies() {
		return speciesRepository.findAll(Sort.by(Sort.Direction.ASC, "designation"));
	}
	
	public Optional<Species> getSpeciesById(Integer id) {
		return speciesRepository.findById(id);
	}
	
	public Species addOneSpecies(Species species) {
		return speciesRepository.save(species);
	}
	
	public void deleteSpecies(int id) {
		Iterable<Animal> animals = animalService.getAnimalsBySpecies(id);
		if (animals != null) {
			animals.forEach(animal -> {
				int idAnimal = animal.getAnimalId();
				Iterable<Treatment> treatments = treatmentService.getTreatmentsByAnimal(idAnimal);
				if (treatments != null) {
					treatments.forEach(treatment -> {
						int idTreatment = treatment.getTreatmentId();
						treatmentService.deleteTreatment(idTreatment);
					});
				}
				animalService.deleteAnimal(idAnimal);
			});
		}		
		speciesRepository.deleteById(id);
	}

	public Species saveSpecies(Species species) {
		Species savedSpecies = speciesRepository.save(species);
		return savedSpecies;
	}

}
