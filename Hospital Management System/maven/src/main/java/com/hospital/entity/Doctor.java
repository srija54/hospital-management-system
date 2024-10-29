package com.hospital.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
	public class Doctor {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int did;

	    @NotBlank(message = "Doctor name cannot be empty")
	    private String dname;

	    @NotBlank(message = "Doctor speciality cannot be empty")
	    private String dspeciality;

	    @NotBlank(message = "Doctor contact cannot be empty")
	    private String dcontact;

	    // Default constructor
	    public Doctor() {}

	    // Parameterized constructor
	    public Doctor(String dname, String dspeciality, String dcontact) {
	        this.dname = dname;
	        this.dspeciality = dspeciality;
	        this.dcontact = dcontact;
	    }

	    // Getters and Setters
	    public int getDid() {
	        return did;
	    }

	    public void setDid(int did) {
	        this.did = did;
	    }

	    public String getDname() {
	        return dname;
	    }

	    public void setDname(String dname) {
	        this.dname = dname;
	    }

	    public String getDspeciality() {
	        return dspeciality;
	    }

	    public void setDspeciality(String dspeciality) {
	        this.dspeciality = dspeciality;
	    }

	    public String getDcontact() {
	        return dcontact;
	    }

	    public void setDcontact(String dcontact) {
	        this.dcontact = dcontact;
	    }
	}



