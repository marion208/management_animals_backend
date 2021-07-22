package com.animals.breeding.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="animals")
public class Animal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_animals")
	private int animalId;

	@Column(name="name")
	private String name;
	
	@Column(name="identification_number")
	private int identificationNumber;
	
	@Column(name="birth_date")
	private Date birthDate;
	
	@Column(name="notes")
	private String notes;
	
	@ManyToOne
	@JoinColumn(name="gender")
	private Gender gender;
	
	@ManyToOne
	@JoinColumn(name="species_id")
	private Species species;
	
	public Gender getGender() {
		return gender;
	}
	
	public void setGender(Gender animalGender) {
		this.gender = animalGender;
	}
	
	public Species getSpecies() {
		return species;
	}
	
	public void setSpecies(Species animalSpecies) {
		this.species = animalSpecies;
	}
	
	public int getAnimalId() {
		return animalId;
	}
	
	public void setAnimalId(int id) {
		this.animalId = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getIdentificationNumber() {
		return identificationNumber;
	}
	
	public void setIdentificationNumber(Integer identificationNumber) {
		this.identificationNumber = identificationNumber;
	}
	
	public Date getBirthDate() {
		return birthDate;
	}
	
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	public String getNotes() {
		return notes;
	}
	
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
}
