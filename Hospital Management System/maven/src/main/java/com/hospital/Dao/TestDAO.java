package com.hospital.Dao;


	import java.util.List;

import com.hospital.entity.Test;

	public interface TestDAO {
	    void create(Test test);
	    Test read(int testid);
	    void update(Test test);
	    void delete(int testid);
	    List<Test> getAllTests();
	}



