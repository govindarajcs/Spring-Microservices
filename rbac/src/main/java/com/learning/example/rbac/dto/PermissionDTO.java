package com.learning.example.rbac.dto;

public class PermissionDTO {
	
	Integer id;
	
	String permission;
	
	Integer roleId;
	
	Integer serviceId;

	public PermissionDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PermissionDTO(Integer id, String permission, Integer roleId, Integer serviceId) {
		super();
		this.id = id;
		this.permission = permission;
		this.roleId = roleId;
		this.serviceId = serviceId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}
	
	

}
