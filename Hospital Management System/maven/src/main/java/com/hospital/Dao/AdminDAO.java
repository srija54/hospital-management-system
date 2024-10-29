package com.hospital.Dao;


	import java.util.List;

import com.hospital.entity.Admin;

	public interface AdminDAO {
	    void create(Admin admin);
	    Admin read(int aid);
	    void update(Admin admin);
	    void delete(int aid);
	    List<Admin> getAllAdmins();
	}



