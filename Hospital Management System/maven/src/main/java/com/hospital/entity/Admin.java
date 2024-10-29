package com.hospital.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
	public class Admin {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int aid;

	    @NotBlank(message = "Admin name cannot be empty")
	    private String aname;

	    // Default constructor
	    public Admin() {}

	    // Parameterized constructor
	    public Admin(String aname) {
	        this.aname = aname;
	    }

	    // Getters and Setters
	    public int getAid() {
	        return aid;
	    }

	    public void setAid(int aid) {
	        this.aid = aid;
	    }

	    public String getAname() {
	        return aname;
	    }

	    public void setAname(String aname) {
	        this.aname = aname;
	    }
	}



