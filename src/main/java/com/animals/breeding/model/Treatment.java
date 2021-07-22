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
@Table(name="treatments")
public class Treatment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_treatment")
	private int treatmentId;

	@Column(name="description")
	private String description;
	
	@Column(name="notes")
	private String notes;
	
	@Column(name="date_treatment")
	private Date treatmentDate;
	
	@Column(name="date_renewal")
	private Date renewalDate;
	
	@ManyToOne
	@JoinColumn(name="animal_id")
	private Animal animal;
	
	public Animal getAnimal() {
		return animal;
	}
	
	public void setAnimal(Animal treatmentAnimal) {
		this.animal = treatmentAnimal;
	}
	
	public int getTreatmentId() {
		return treatmentId;
	}
	
	public void setTreatmentId(int id) {
		this.treatmentId = id;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getNotes() {
		return notes;
	}
	
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	public Date getDateTreatment() {
		return treatmentDate;
	}
	
	public void setDateTreatment(Date treatmentDate) {
		this.treatmentDate = treatmentDate;
	}
	
	public Date getDateRenewal() {
		return renewalDate;
	}
	
	public void setDateRenewal(Date renewalDate) {
		this.renewalDate = renewalDate;
	}
	
}
