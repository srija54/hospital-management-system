package com.hospital.entity;

import java.sql.Date;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
	public class MedicalRecord {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int mcid;

	    @NotBlank(message = "Medical problem cannot be empty")
	    private String mproblem;

	    private Date mdate;

	    @ManyToOne
	    @JoinColumn(name = "pid")
	    private Patient patient;

	    // Default constructor
	    public MedicalRecord() {}

	    // Parameterized constructor
	    public MedicalRecord(String mproblem, Date mdate, Patient patient) {
	        this.mproblem = mproblem;
	        this.mdate = mdate;
	        this.patient = patient;
	    }

	    // Getters and Setters
	    public int getMcid() {
	        return mcid;
	    }

	    public void setMcid(int mcid) {
	        this.mcid = mcid;
	    }

	    public String getMproblem() {
	        return mproblem;
	    }

	    public void setMproblem(String mproblem) {
	        this.mproblem = mproblem;
	    }

	    public Date getMdate() {
	        return mdate;
	    }

	    public void setMdate(Date mdate) {
	        this.mdate = mdate;
	    }

	    public Patient getPatient() {
	        return patient;
	    }

	    public void setPatient(Patient patient) {
	        this.patient = patient;
	    }
	}



