package com.animals.breeding.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.animals.breeding.model.Gender;

@Repository
public interface GenderRepository extends CrudRepository<Gender, Integer> {
	
	Iterable<Gender> findAll(Sort sort);
}
