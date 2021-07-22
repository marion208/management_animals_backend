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
@Table(name="weighings")
public class Weighing {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_weighing")
	private int weighingId;

	@Column(name="weigh_value")
	private int weighValue;

	@Column(name="date_weighing")
	private Date weighingDate;
	
	@ManyToOne
	@JoinColumn(name="animal_id")
	private Animal animal;
	
	public Animal getAnimal() {
		return animal;
	}
	
	public void setAnimal(Animal weighingAnimal) {
		this.animal = weighingAnimal;
	}
	
	public int getWeighingId() {
		return weighingId;
	}
	
	public void setWeighingId(int id) {
		this.weighingId = id;
	}
		
	public int getWeighValue() {
		return weighValue;
	}
	
	public void setWeighValue(Integer value) {
		this.weighValue = value;
	}
	
	public Date getWeighingDate() {
		return weighingDate;
	}
	
	public void setWeighingDate(Date weighingDate) {
		this.weighingDate = weighingDate;
	}
	
}
