package com.library.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.admin.model.entity.Admin;


@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
    
	Admin findByAdminAcc(String adminAcc);
	
}