package com.animals.breeding.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.animals.breeding.model.Animal;
import com.animals.breeding.model.Species;

@Repository
public interface AnimalRepository extends CrudRepository<Animal, Integer>{
	
	Iterable<Animal> findAll(Sort sort);

	Iterable<Animal> findBySpecies(Species species);

}
