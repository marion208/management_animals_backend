package com.animals.breeding.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.animals.breeding.model.Animal;
import com.animals.breeding.model.Treatment;

@Repository
public interface TreatmentRepository extends CrudRepository<Treatment, Integer> {

	public Iterable<Treatment> findByAnimal(Animal animal, Sort sort);
	
}
