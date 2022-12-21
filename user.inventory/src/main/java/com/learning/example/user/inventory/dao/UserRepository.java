package com.learning.example.user.inventory.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.learning.example.user.inventory.entity.UserDetail;

public interface UserRepository extends JpaRepository<UserDetail, String> {
	
	public UserDetail findFirstByRoleId(Integer roleId);
	
	public List<UserDetail> findByEmail(String email);
	
	public List<UserDetail> findByPhoneNo(Integer phoneNo);

}
