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
import org.springframework.web.bind.annotation.RestController;

import com.learning.example.rbac.dto.EndpointDTO;
import com.learning.example.rbac.entity.response.EndpointResponse;
import com.learning.example.rbac.service.EndpointService;

@RestController
@RequestMapping("/endpoint")
public class EndpointController {
	
	@Autowired
	EndpointService service;
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EndpointResponse> createEndpoint(@RequestBody EndpointDTO dto) {
		return service.saveServiceEndpoint(dto);
	}
	
	@DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/{id}")
	public ResponseEntity<EndpointResponse> deleteEndpoint(@PathVariable("id") Integer id) {
		return service.deleteEndpoint(id);
	}
	
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, path = "/{id}")
	public ResponseEntity<EndpointResponse> updateEndpoint(@RequestBody EndpointDTO dto, @PathVariable("id") Integer id) {
		dto.setId(id);
		return service.updateEndpoint(dto);
	}
	
	@GetMapping(path="/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EndpointResponse> retrieveEndpoint(@PathVariable("id") Integer id) {
		return service.getServiceEndpoint(id);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<EndpointResponse>> retrieveAllEndpoint() {
		return service.getAll();
	}
}
