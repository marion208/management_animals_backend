package com.animals.breeding.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="species")
public class Species {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_species")
	private int speciesId;
	
	@Column(name="designation")
	private String designation;
	
	@OneToMany(
			mappedBy="species",
			orphanRemoval = true)
	List<Animal> animals = new ArrayList<>();

	public int getSpeciesId() {
		return speciesId;
	}
	
	public void setSpeciesId(int id) {
		this.speciesId = id;
	}
	
	public String getDesignation() {
		return designation;
	}
	
	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public void addAnimal(Animal animal) {
		animals.add(animal);
		animal.setSpecies(this);
	}
	
	public void removeAnimal(Animal animal) {
		animals.remove(animal);
		animal.setSpecies(null);
	}
	
}
