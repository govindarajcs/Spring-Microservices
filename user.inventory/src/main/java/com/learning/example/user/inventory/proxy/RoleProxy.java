package com.learning.example.user.inventory.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.learning.example.user.inventory.dto.RolesDTO;

@FeignClient(name="rbac/rbac")
public interface RoleProxy {
	
	@GetMapping("/role")
	public ResponseEntity<List<RolesDTO>> getAllRoles();
	

}
