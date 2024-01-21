package com.library.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.user.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
                       
	User findByUserName(String username);
	User findByPhoneNumber(String phoneNumber);
}
