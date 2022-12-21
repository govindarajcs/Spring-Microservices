package com.learning.example.rbac.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learning.example.rbac.dto.PermissionDTO;
import com.learning.example.rbac.entity.response.PermissionResponse;
import com.learning.example.rbac.service.PermissionService;

@RestController
@RequestMapping("/permission")
public class PermissionController {

	@Autowired
	PermissionService service;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PermissionResponse> createPermission(@RequestBody PermissionDTO dto) {
		return service.createPermission(dto);
	}

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, path = "/{id}")
	public ResponseEntity<PermissionResponse> updatePermission(@RequestBody PermissionDTO dto,
			@PathVariable("id") Integer id) {
		dto.setId(id);
		return service.updatePermission(dto);
	}

	@DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PermissionResponse> deletePermission(@PathVariable("id") Integer id) {
		return service.deletePermission(id);
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PermissionResponse>> getAllPermissions() {
		return service.getAllPermission();
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/{id}")
	public ResponseEntity<PermissionResponse> getPermissionById(@PathVariable("id") Integer id) {
		return service.getPermissionById(id);
	}

	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path="/get_permission_level") 
	public ResponseEntity<PermissionResponse> checkPermission(@RequestParam Integer roleId, @RequestParam Integer endpointId) { return
	  service.getPermission(roleId, endpointId); 
	}
	  
	 

}
