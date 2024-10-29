package com.hospital.Dao;


	import java.util.List;

import com.hospital.entity.Patient;

	public interface PatientDAO {
	    void create(Patient patient);
	    Patient read(int pid);
	    void update(Patient patient);
	    void delete(int pid);
	    List<Patient> getAllPatients();
	}



