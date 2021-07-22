package com.animals.breeding.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.animals.breeding.model.Species;

@Repository
public interface SpeciesRepository extends CrudRepository<Species, Integer> {
	
	Iterable<Species> findAll(Sort sort);
}
