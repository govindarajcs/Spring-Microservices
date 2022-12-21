package com.learning.example.rbac.dto;


import java.util.ArrayList;
import java.util.List;

import com.learning.example.rbac.entity.Permission;
import com.learning.example.rbac.entity.Role;

public class RoleDTO {
	Integer Id;
	String name;
	List<PermissionDTO> permissionDTOList;
	
	public List<PermissionDTO> getPermissionDTOList() {
		return permissionDTOList;
	}

	public void setPermissionDTOList(List<PermissionDTO> permissionDTOList) {
		this.permissionDTOList = permissionDTOList;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public RoleDTO(String name) {
		super();
		this.name = name;
	}

	public RoleDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void convertEntityToDTO(Role role) {
		this.setId(role.getId());
		this.setName(role.getRoleName());
		List<Permission> permissionList = role.getPermissions();
		ArrayList<PermissionDTO> permissionDTOList = new ArrayList<PermissionDTO>();
		for(Permission permission:permissionList) {
			PermissionDTO dto = new PermissionDTO(permission.getId(), permission.getPermission(), role.getId(), permission.getService().getId());
			permissionDTOList.add(dto);
		}
		this.setPermissionDTOList(permissionDTOList);
	}
	
}
