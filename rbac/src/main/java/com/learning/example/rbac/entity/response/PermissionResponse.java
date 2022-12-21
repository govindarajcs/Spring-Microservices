package com.learning.example.rbac.entity.response;

public class PermissionResponse {

	Integer id;
	
	String permission;
	
	String link;
	
	EndpointResponse endpointResponse;
	
	String message;
	
	RolesResponse roleResponse;
	

	public PermissionResponse(String message) {
		super();
		this.message = message;
	}

	public PermissionResponse(Integer id, String permission, String link, EndpointResponse endpointResponse,
			RolesResponse roleResponse) {
		super();
		this.id = id;
		this.permission = permission;
		this.link = link;
		this.endpointResponse = endpointResponse;
		this.roleResponse = roleResponse;
	}

	public PermissionResponse(Integer id, String permission, String link, EndpointResponse endpointResponse,
			String message, RolesResponse roleResponse) {
		super();
		this.id = id;
		this.permission = permission;
		this.link = link;
		this.endpointResponse = endpointResponse;
		this.message = message;
		this.roleResponse = roleResponse;
	}

	public PermissionResponse() {
		super();
		// TODO Auto-generated constructor stub
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

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public EndpointResponse getEndpointResponse() {
		return endpointResponse;
	}

	public void setEndpointResponse(EndpointResponse endpointResponse) {
		this.endpointResponse = endpointResponse;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public RolesResponse getRoleResponse() {
		return roleResponse;
	}

	public void setRoleResponse(RolesResponse roleResponse) {
		this.roleResponse = roleResponse;
	}
	
	
	
}
