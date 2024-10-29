package com.hospital.entity;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
	public class Patient {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int pid;

	    @NotBlank(message = "Patient name cannot be empty")
	    private String pname;

	    @NotBlank(message = "Patient contact cannot be empty")
	    private String pcontact;

	    @NotBlank(message = "Patient address cannot be empty")
	    private String paddress;

	    @Past(message = "Date of birth must be in the past")
	    private Date pdob;

	    @ManyToOne
	    @JoinColumn(name = "hid")
	    private Hospital hospital;

	    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
	    private List<MedicalRecord> medicalRecords;

	    // Default constructor
	    public Patient() {}

	    // Parameterized constructor
	    public Patient(String pname, String pcontact, String paddress, Date pdob, Hospital hospital) {
	        this.pname = pname;
	        this.pcontact = pcontact;
	        this.paddress = paddress;
	        this.pdob = pdob;
	        this.hospital = hospital;
	    }

	    // Getters and Setters
	    public int getPid() {
	        return pid;
	    }

	    public void setPid(int pid) {
	        this.pid = pid;
	    }

	    public String getPname() {
	        return pname;
	    }

	    public void setPname(String pname) {
	        this.pname = pname;
	    }

	    public String getPcontact() {
	        return pcontact;
	    }

	    public void setPcontact(String pcontact) {
	        this.pcontact = pcontact;
	    }

	    public String getPaddress() {
	        return paddress;
	    }

	    public void setPaddress(String paddress) {
	        this.paddress = paddress;
	    }

	    public Date getPdob() {
	        return pdob;
	    }

	    public void setPdob(Date pdob) {
	        this.pdob = pdob;
	    }

	    public Hospital getHospital() {
	        return hospital;
	    }

	    public void setHospital(Hospital hospital) {
	        this.hospital = hospital;
	    }

	    public List<MedicalRecord> getMedicalRecords() {
	        return medicalRecords;
	    }

	    public void setMedicalRecords(List<MedicalRecord> medicalRecords) {
	        this.medicalRecords = medicalRecords;
	    }
	}



