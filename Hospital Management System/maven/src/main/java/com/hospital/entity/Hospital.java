package com.hospital.entity;


import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
	public class Hospital {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int hid;

	    @NotBlank(message = "Hospital name cannot be empty")
	    private String hname;

	    @NotBlank(message = "Hospital address cannot be empty")
	    private String haddress;

	    @NotBlank(message = "Hospital contact cannot be empty")
	    private String hcontact;

	    @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL)
	    private List<Patient> patients;

	    // Default constructor
	    public Hospital() {}

	    // Parameterized constructor
	    public Hospital(String hname, String haddress, String hcontact) {
	        this.hname = hname;
	        this.haddress = haddress;
	        this.hcontact = hcontact;
	    }

	    // Getters and Setters
	    public int getHid() {
	        return hid;
	    }

	    public void setHid(int hid) {
	        this.hid = hid;
	    }

	    public String getHname() {
	        return hname;
	    }

	    public void setHname(String hname) {
	        this.hname = hname;
	    }

	    public String getHaddress() {
	        return haddress;
	    }

	    public void setHaddress(String haddress) {
	        this.haddress = haddress;
	    }

	    public String getHcontact() {
	        return hcontact;
	    }

	    public void setHcontact(String hcontact) {
	        this.hcontact = hcontact;
	    }

	    public List<Patient> getPatients() {
	        return patients;
	    }

	    public void setPatients(List<Patient> patients) {
	        this.patients = patients;
	    }
	}



