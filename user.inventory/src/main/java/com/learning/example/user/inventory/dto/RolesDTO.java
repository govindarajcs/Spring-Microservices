package com.learning.example.user.inventory.dto;

import java.util.List;
import java.util.Map;


public class RolesDTO {
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

	public RolesDTO(String name) {
		super();
		this.name = name;
	}

	public RolesDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public UserDTO getRolesDTO(UserDTO dto, Map<String, Integer> rolesMap) {
		RolesDTO rolesDTO = new RolesDTO();
		rolesDTO.setId(rolesMap.get(dto.getRole()));
		rolesDTO.setName(dto.getRole());
		rolesDTO.setPermissionDTOList(null);
		dto.setRoleDetails(rolesDTO);
		return dto;
	}
	

}
