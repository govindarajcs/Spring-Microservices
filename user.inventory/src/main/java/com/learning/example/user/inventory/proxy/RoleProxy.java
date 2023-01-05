package com.learning.example.user.inventory.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.learning.example.user.inventory.dto.RolesDTO;

@FeignClient(name="rbac/rbac")
public interface RoleProxy {
	
	@GetMapping("/role")
	public ResponseEntity<List<RolesDTO>> getAllRoles();
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path= "/role/{id}")
	public ResponseEntity<RolesDTO> getRoleById(@PathVariable Integer id);
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path= "/{name}")
	public ResponseEntity<RolesDTO> getRoleByName(@PathVariable("name") String name);

}
