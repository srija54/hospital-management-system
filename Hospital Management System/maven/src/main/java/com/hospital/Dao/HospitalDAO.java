package com.hospital.Dao;


	import java.util.List;

import com.hospital.entity.Hospital;

	public interface HospitalDAO {
	    void create(Hospital hospital);
	    Hospital read(int hid);
	    void update(Hospital hospital);
	    void delete(int hid);
	    List<Hospital> getAllHospitals();
	}



