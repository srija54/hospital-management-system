package com.hospital.Dao;


	import java.util.List;

import com.hospital.entity.Doctor;

	public interface DoctorDAO {
	    void create(Doctor doctor);
	    Doctor read(int did);
	    void update(Doctor doctor);
	    void delete(int did);
	    List<Doctor> getAllDoctors();
	}



