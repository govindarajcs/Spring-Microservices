package com.learning.example.rbac.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.example.rbac.entity.Role;

public interface RoleDao extends JpaRepository<Role, Integer> {
	
	public Optional<Role> findByRoleName(String name);

}
