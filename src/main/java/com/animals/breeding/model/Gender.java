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
@Table(name="gender")
public class Gender {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_gender")
	private int genderId;
	
	@Column(name="description")
	private String description;
	
	@OneToMany(
			mappedBy="gender",
			orphanRemoval = true)
	List<Animal> animals = new ArrayList<>();

	public int getGenderId() {
		return genderId;
	}
	
	public void setGenderId(int id) {
		this.genderId = id;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

}
