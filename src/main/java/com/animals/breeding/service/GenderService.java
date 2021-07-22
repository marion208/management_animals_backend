package com.animals.breeding.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.animals.breeding.model.Gender;
import com.animals.breeding.repository.GenderRepository;

@Service
public class GenderService {
	
	@Autowired
	private GenderRepository genderRepository;
	
	public Iterable<Gender> getGenders() {
		return genderRepository.findAll(Sort.by(Sort.Direction.ASC, "genderId"));
	}
	
	public Optional<Gender> getGenderById(Integer id) {
		return genderRepository.findById(id);
	}
	
	public Gender addGender(Gender gender) {
		return genderRepository.save(gender);
	}

}
