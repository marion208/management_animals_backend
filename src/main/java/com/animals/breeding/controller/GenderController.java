package com.animals.breeding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.animals.breeding.model.Gender;
import com.animals.breeding.service.GenderService;

@RestController
public class GenderController {
	
	@Autowired
	private GenderService genderService;
	
	/**
	 * Read - Get all genders
	 * @return - An Iterable object of Species
	 */
	@CrossOrigin
	@GetMapping("/genders")
	public Iterable<Gender> getGenders() {
		return genderService.getGenders();
	}
	
}
