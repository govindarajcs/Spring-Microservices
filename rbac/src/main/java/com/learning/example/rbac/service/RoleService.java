package com.learning.example.rbac.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.example.rbac.dao.RoleDao;
import com.learning.example.rbac.dto.RoleDTO;
import com.learning.example.rbac.entity.Role;

@Service
public class RoleService {
	
	@Autowired
	RoleDao dao;
	
	public RoleDTO createRole(RoleDTO roleDTO) {
		RoleDTO updatedRoleDTO = null;
		try {
			Role role = new Role();
			role.setRoleName(roleDTO.getName());
			if (roleDTO.getId()!=null) {
				role.setId(roleDTO.getId());
			}
			updatedRoleDTO = new RoleDTO();
			updatedRoleDTO.convertEntityToDTO(dao.save(role));
		} catch(Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
		return updatedRoleDTO;
	}
	
	
	public RoleDTO getRoleDetailsByName(String roleName) {
		Optional<Role> optionalRole = dao.findByRoleName(roleName);
		RoleDTO dto = null;
		if (optionalRole.isPresent()) {
			Role role = optionalRole.get();
			dto = new RoleDTO(role.getRoleName());
			dto.setId(role.getId());
		}
		return dto;
	}
	
	public Integer deleteRoleDTO(Integer roleId) {
		int status = 0; //0 - success; 1 - record not found 2 - failed due to issue
		try {
			Optional<Role> optionalRole = dao.findById(roleId);

			if (optionalRole.isPresent()) {
				Role role = optionalRole.get();
				dao.delete(role);
				status = 0;
			} else {
				status = 1;
			}
		} catch(Exception e) {
			System.out.println(e.getLocalizedMessage());
			status = 2;
		}
		return status;
	}

	public RoleDTO getRoleDetailsById(Integer id) {
		Optional<Role> optionalRole = dao.findById(id);
		RoleDTO dto = null;
		if (optionalRole.isPresent()) {
			Role role = optionalRole.get();
			dto = new RoleDTO(role.getRoleName());
			dto.setId(role.getId());
		}
		return dto;
	}
	
	public List<RoleDTO> getAllRoles() {
		List<Role> roleList= dao.findAll();
		List<RoleDTO> roleDTOList = new ArrayList<RoleDTO>();
		for(Role role: roleList) {
			RoleDTO dto = new RoleDTO();
			dto.convertEntityToDTO(role);
			roleDTOList.add(dto);
		}
		return roleDTOList;
	}
}
