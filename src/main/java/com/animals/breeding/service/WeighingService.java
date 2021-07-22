package com.animals.breeding.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import com.animals.breeding.model.Animal;
import com.animals.breeding.model.Weighing;
import com.animals.breeding.repository.WeighingRepository;

@Service
public class WeighingService {
	
	@Autowired
	private WeighingRepository weighingRepository;
	
	@Autowired
	private AnimalService animalService;
	
	public Iterable<Weighing> getWeighings() {
		return weighingRepository.findAll();
	}
	
	public Iterable<Weighing> getWeighingsByAnimal(int animalId) {
		Optional<Animal> optAnimal = animalService.getAnimalById(animalId);
		Animal animal = optAnimal.get();
		return weighingRepository.findByAnimal(animal, Sort.by(Sort.Direction.ASC, "weighingDate"));
	}
	
	public Optional<Weighing> getWeighingById(Integer id) {
		return weighingRepository.findById(id);
	}
	
	public Weighing addWeighing(Weighing weighing) {
		return weighingRepository.save(weighing);
	}
	
	public void deleteWeighing(int id) {
		weighingRepository.deleteById(id);
	}

	public Weighing saveWeighing(Weighing weighing) {
		Weighing savedWeighing = weighingRepository.save(weighing);
		return savedWeighing;
	}

	public Weighing getLatestWeighByAnimal(int animalId) {
		Optional<Animal> optAnimal = animalService.getAnimalById(animalId);
		Animal animal = optAnimal.get();
		Iterable<Weighing> weighings = weighingRepository.findByAnimal(animal, Sort.by(Sort.Direction.DESC, "weighingDate"));
		List<Weighing> listWeighings = Streamable.of(weighings).toList();
		Weighing latestWeighing = listWeighings.get(0);
		return latestWeighing;
	}
	
}
