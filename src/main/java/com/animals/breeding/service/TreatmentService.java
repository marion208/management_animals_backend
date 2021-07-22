package com.animals.breeding.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.animals.breeding.model.Animal;
import com.animals.breeding.model.Treatment;
import com.animals.breeding.repository.TreatmentRepository;

@Service
public class TreatmentService {
	
	@Autowired
	private TreatmentRepository treatmentRepository;
	
	@Autowired
	private AnimalService animalService;
	
	public Iterable<Treatment> getTreatments() {
		return treatmentRepository.findAll();
	}
	
	public Iterable<Treatment> getTreatmentsByAnimal(int animalId) {
		Optional<Animal> optAnimal = animalService.getAnimalById(animalId);
		Animal animal = optAnimal.get();
		return treatmentRepository.findByAnimal(animal, Sort.by(Sort.Direction.ASC, "treatmentDate"));
	}
	
	public Iterable<Treatment> getRenewalTreatmentsByAnimal(int animalId) {
		Optional<Animal> optAnimal = animalService.getAnimalById(animalId);
		Animal animal = optAnimal.get();
		return treatmentRepository.findByAnimal(animal, Sort.by(Sort.Direction.DESC, "renewalDate"));
	}
	
	public Optional <Treatment> getTreatmentById(Integer id) {
		return treatmentRepository.findById(id);
	}
	
	public Treatment addTreatment(Treatment treatment) {
		return treatmentRepository.save(treatment);
	}
	
	public void deleteTreatment(int id) {
		treatmentRepository.deleteById(id);
	}

	public Treatment saveTreatment(Treatment treatment) {
		Treatment savedTreatment = treatmentRepository.save(treatment);
		return savedTreatment;
	}

}
