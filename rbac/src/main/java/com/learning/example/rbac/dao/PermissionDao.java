package com.learning.example.rbac.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.example.rbac.entity.Endpoint;
import com.learning.example.rbac.entity.Permission;
import com.learning.example.rbac.entity.Role;

public interface PermissionDao extends JpaRepository<Permission, Integer> {
	
	public Permission getPermissionByServiceAndRole(Endpoint endpoint, Role role);

}
