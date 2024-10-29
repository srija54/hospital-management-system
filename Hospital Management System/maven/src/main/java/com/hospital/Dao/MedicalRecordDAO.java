package com.hospital.Dao;


	import java.util.List;

import com.hospital.entity.MedicalRecord;

	public interface MedicalRecordDAO {
	    void create(MedicalRecord medicalRecord);
	    MedicalRecord read(int mcid);
	    void update(MedicalRecord medicalRecord);
	    void delete(int mcid);
	    List<MedicalRecord> getAllMedicalRecords();
	}



