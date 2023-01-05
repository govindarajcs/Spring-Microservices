package com.learning.example.rbac.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.learning.example.rbac.dto.RoleDTO;
import com.learning.example.rbac.entity.response.RolesResponse;
import com.learning.example.rbac.service.RoleService;

@RestController
@RequestMapping("/role")
public class RolesController {
	
	@Autowired
	RoleService service;
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RolesResponse> createRole(@RequestBody RoleDTO roleDTO) {
		ResponseEntity<RolesResponse> response = null;
		// check if the roll name  exists
		if (service.getRoleDetailsByName(roleDTO.getName())==null) {
			RoleDTO updatedRoleDTO = service.createRole(roleDTO);
			if (updatedRoleDTO != null) {
				String link = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/").path(updatedRoleDTO.getId().toString()).build().toUriString();
				response = new ResponseEntity<RolesResponse>(new RolesResponse("Role created successfully", updatedRoleDTO.getName(), updatedRoleDTO.getId(), link), HttpStatus.CREATED);
			} else {
				response = new ResponseEntity<RolesResponse>(new RolesResponse("Role Creation Failed"), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			response = new ResponseEntity<RolesResponse>(new RolesResponse("Role "+roleDTO.getName()+" already exist"), HttpStatus.NOT_ACCEPTABLE);
		}
		return response;
	}
	
	@PutMapping(path="/{role_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RolesResponse> updateRole(@RequestBody RoleDTO roleDTO, @PathVariable("role_id") Integer id) {
		ResponseEntity<RolesResponse> response = null;
		// check if roll name exist
		RoleDTO retrievedRoleDTO = service.getRoleDetailsById(id);
		if (retrievedRoleDTO!=null) {
			roleDTO.setId(retrievedRoleDTO.getId());
			RoleDTO updatedRoleDTO = service.createRole(roleDTO);
			if (updatedRoleDTO != null) {
				String link = ServletUriComponentsBuilder.fromCurrentRequestUri().build().toUriString();
				response = new ResponseEntity<RolesResponse>(new RolesResponse("Role updated successfully", updatedRoleDTO.getName(), updatedRoleDTO.getId(), link), HttpStatus.CREATED);
			} else {
				response = new ResponseEntity<RolesResponse>(new RolesResponse("Role Updation Failed"), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			response = new ResponseEntity<RolesResponse>(new RolesResponse("Role "+id+" doesn't exist to update"), HttpStatus.NOT_FOUND);
		}
		return response;
	}
	
	@DeleteMapping(path="/{role_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RolesResponse> deleteRole(@PathVariable("role_id") Integer id) {
		Integer status = service.deleteRoleDTO(id);
		ResponseEntity<RolesResponse> response = null;
		if (status == 0) {
			response = new ResponseEntity<RolesResponse>(new RolesResponse("Role id "+id+" deleted Successfully"), HttpStatus.NO_CONTENT);
		} else if (status == 1) {
			response = new ResponseEntity<RolesResponse>(new RolesResponse("Role id "+id+" doesn't exist"), HttpStatus.NOT_FOUND);
		} else {
			response = new ResponseEntity<RolesResponse>(new RolesResponse("Role id "+id+" deletion is not Successful"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path= "/{name}")
	public ResponseEntity<RoleDTO> getRoleByName(@PathVariable("name") String name) {
		RoleDTO dto = service.getRoleDetailsByName(name);
		ResponseEntity<RoleDTO> response = null;
		if (dto!=null) {
			response = new ResponseEntity<RoleDTO>(dto, HttpStatus.OK);
		} else {
			response = new ResponseEntity<RoleDTO>(HttpStatus.NOT_FOUND);
		}
		return response;
	}

		
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path= "/{id}")
	public ResponseEntity<RoleDTO> getRole(@PathVariable("id") Integer id) {
		RoleDTO dto = service.getRoleDetailsById(id);
		ResponseEntity<RoleDTO> response = null;
		if (dto!=null) {
			response = new ResponseEntity<RoleDTO>(dto, HttpStatus.OK);
		} else {
			response = new ResponseEntity<RoleDTO>(HttpStatus.NOT_FOUND);
		}
		return response;
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RoleDTO>> getAllRoles() {
		System.out.println("I am from user inventory");
		ResponseEntity<List<RoleDTO>> response = new ResponseEntity<List<RoleDTO>>(service.getAllRoles(),HttpStatus.OK);
		return response;
	}

}
