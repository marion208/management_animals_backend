package com.animals.breeding.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.animals.breeding.model.Animal;
import com.animals.breeding.model.Species;
import com.animals.breeding.model.Treatment;
import com.animals.breeding.repository.AnimalRepository;

@Service
public class AnimalService {
	
	@Autowired
	private AnimalRepository animalRepository;
	
	@Autowired
	private TreatmentService treatmentService;
	
	@Autowired
	private SpeciesService speciesService;
		
	public Iterable<Animal> getAnimals() {
		return animalRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
	}
	
	public Iterable<Animal> getAnimalsBySpecies(int speciesId) {
		Optional<Species> optSpecies = speciesService.getSpeciesById(speciesId);
		Species species = optSpecies.get();
		return animalRepository.findBySpecies(species);
	}
	
	public Optional<Animal> getAnimalById(Integer id) {
		return animalRepository.findById(id);
	}
	
	public Animal addAnimal(Animal animal) {
		return animalRepository.save(animal);
	}
	
	public void deleteAnimal(int id) {
		Iterable<Treatment> treatments = treatmentService.getTreatmentsByAnimal(id);
		if (treatments != null) {
			treatments.forEach(treatment -> {
				int idTreatment = treatment.getTreatmentId();
				treatmentService.deleteTreatment(idTreatment);
			});
		}
		
		animalRepository.deleteById(id);
	}

	public Animal saveAnimal(Animal animal) {
		Animal savedAnimal = animalRepository.save(animal);
		return savedAnimal;
	}

}
