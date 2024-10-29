package com.hospital.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
	public class Test {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int testid;

	    @NotBlank(message = "Test name cannot be empty")
	    private String tname;

	    private double tprice;

	    // Default constructor
	    public Test() {}

	    // Parameterized constructor
	    public Test(String tname, double tprice) {
	        this.tname = tname;
	        this.tprice = tprice;
	    }

	    // Getters and Setters
	    public int getTestid() {
	        return testid;
	    }

	    public void setTestid(int testid) {
	        this.testid = testid;
	    }

	    public String getTname() {
	        return tname;
	    }

	    public void setTname(String tname) {
	        this.tname = tname;
	    }

	    public double getTprice() {
	        return tprice;
	    }

	    public void setTprice(double tprice) {
	        this.tprice = tprice;
	    }
	}


