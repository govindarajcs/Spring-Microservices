package com.learning.example.rbac.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.learning.example.rbac.dao.EndpointDao;
import com.learning.example.rbac.dao.PermissionDao;
import com.learning.example.rbac.dao.RoleDao;
import com.learning.example.rbac.dto.PermissionDTO;
import com.learning.example.rbac.entity.Endpoint;
import com.learning.example.rbac.entity.Permission;
import com.learning.example.rbac.entity.Role;
import com.learning.example.rbac.entity.response.EndpointResponse;
import com.learning.example.rbac.entity.response.PermissionResponse;
import com.learning.example.rbac.entity.response.RolesResponse;

@Service
public class PermissionService {
	
	@Autowired
	PermissionDao dao;
	
	@Autowired
	EndpointDao endpointDao;
	
	@Autowired
	RoleDao roleDao;
	
	public ResponseEntity<PermissionResponse> createPermission(PermissionDTO dto) {
		Role role = null;
		Endpoint endpoint = null;
		ResponseEntity<PermissionResponse> response;
		boolean isPermissionObjectExist = false;
		try {
			if (dto.getServiceId()!=null && dto.getRoleId()!=null) {
				Optional<Role> roleOptional = roleDao.findById(dto.getRoleId());
				Optional<Endpoint> endpointOptional = endpointDao.findById(dto.getServiceId());

				if (roleOptional.isPresent()) {
					role = roleOptional.get();
				}

				if(endpointOptional.isPresent()) {
					endpoint = endpointOptional.get();
				}
				isPermissionObjectExist = this.isPermissionByNameAndMethodExist(endpoint, role);
				if (!isPermissionObjectExist) {
					Permission entity = new Permission();
					entity.setPermission(dto.getPermission());
					entity.setRole(role);
					entity.setService(endpoint);
					Permission updatedEntity = dao.save(entity); 
					response = new ResponseEntity<PermissionResponse>(new PermissionResponse(updatedEntity.getId(), updatedEntity.getPermission(), ServletUriComponentsBuilder.fromCurrentRequest().path("/")
							.path(updatedEntity.getId().toString()).build().toUriString(), 
							new EndpointResponse(endpoint.getSuffixPath(), endpoint.getId(), endpoint.getHttpMethod(), ServletUriComponentsBuilder.fromCurrentRequest().path("/").path(endpoint.getId().toString()).build().toUriString()), 
							"Permission Record created Successfully",
							new RolesResponse(role.getRoleName(), role.getId(), ServletUriComponentsBuilder.fromCurrentRequest().path("/").path(role.getId().toString()).build().toUriString())
							), HttpStatus.CREATED);
				} else {
					response = new ResponseEntity<PermissionResponse>(new PermissionResponse("Record already exists"), HttpStatus.BAD_REQUEST);
				}
			} else {
				response = new ResponseEntity<PermissionResponse>(new PermissionResponse("Mandatory parameters roles, endpoint details missing"), HttpStatus.BAD_REQUEST);
			}
		} catch(Exception e) {
			response = new ResponseEntity<PermissionResponse>(new PermissionResponse("Permission record creation failed due to "+e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;	
	}
	
	public ResponseEntity<PermissionResponse> updatePermission(PermissionDTO dto) {
		ResponseEntity<PermissionResponse> response;
		Optional<Permission> optionalPermission = dao.findById(dto.getId());
		if (optionalPermission.isPresent()) {
			Permission permission = optionalPermission.get();
			permission.setPermission(dto.getPermission()!=null?dto.getPermission():permission.getPermission());
			
			if (dto.getRoleId()!=null) {
				Optional<Role> optionalRole = roleDao.findById(dto.getRoleId());
				if (optionalRole.isPresent()) {
					Role role = optionalRole.get();
					permission.setRole(role);
				} else {
					response = new ResponseEntity<PermissionResponse>(new PermissionResponse("Role id "+dto.getRoleId()+" doesn't exist"), HttpStatus.BAD_REQUEST);
				}
			}
			
			if (dto.getServiceId()!=null) {
				Optional<Endpoint> optionalEndpoint = endpointDao.findById(dto.getRoleId());
				if (optionalEndpoint.isPresent()) {
					Endpoint endPoint = optionalEndpoint.get();
					permission.setService(endPoint);
				} else {
					response = new ResponseEntity<PermissionResponse>(new PermissionResponse("Endpoint id "+dto.getServiceId()+" doesn't exist"), HttpStatus.BAD_REQUEST);
				}
			}
			dao.save(permission);
			response = new ResponseEntity<PermissionResponse>(new PermissionResponse(dto.getId(), permission.getPermission(), ServletUriComponentsBuilder.fromCurrentRequest().path("/")
							.path(permission.getId().toString()).build().toUriString(), 
							new EndpointResponse(permission.getService().getSuffixPath(), permission.getService().getId(), permission.getService().getHttpMethod(), ServletUriComponentsBuilder.fromCurrentRequest().path("/").path(permission.getService().getId().toString()).build().toUriString()), 
							"Permission Record updated Successfully",
							new RolesResponse(permission.getRole().getRoleName(), permission.getRole().getId(), ServletUriComponentsBuilder.fromCurrentRequest().path("/").path(permission.getRole().getId().toString()).build().toUriString())
							), HttpStatus.CREATED);
		} else {
			response = new ResponseEntity<PermissionResponse>(new PermissionResponse("Permission id "+dto.getId()+" doesn't exist"), HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	public ResponseEntity<PermissionResponse> deletePermission(Integer id) {
		ResponseEntity<PermissionResponse> response;
		Optional<Permission> optionalPermission = dao.findById(id);
		if(optionalPermission.isPresent()) {
			dao.delete(optionalPermission.get());
			response = new ResponseEntity<PermissionResponse>(new PermissionResponse("Permission datail for id "+id+" deleted successfully"), HttpStatus.NO_CONTENT);
		} else {
			response = new ResponseEntity<PermissionResponse>(new PermissionResponse("Record not found"), HttpStatus.NOT_FOUND);
		}
		
		return response;
	}
	
	public ResponseEntity<PermissionResponse> getPermissionById(Integer id) {
		ResponseEntity<PermissionResponse> response;
		Optional<Permission> optionalPermission = dao.findById(id);
		if(optionalPermission.isPresent()) {
			Permission permission = optionalPermission.get();
			String rolePath = ServletUriComponentsBuilder.fromCurrentRequest().toString().split("permission")[0].concat("/role/").concat(permission.getRole().getId().toString());
			String endpointPath = ServletUriComponentsBuilder.fromCurrentRequest().toString().split("permission")[0].concat("/role/").concat(permission.getService().getId().toString());
			response = new ResponseEntity<PermissionResponse>(new PermissionResponse(permission.getId(), permission.getPermission(),
					ServletUriComponentsBuilder.fromCurrentRequest().path("/").path(permission.getService().getId().toString()).build().toUriString(), 
					new EndpointResponse(permission.getService().getSuffixPath(), permission.getService().getId(), permission.getService().getHttpMethod(), endpointPath), 		
					new RolesResponse(permission.getRole().getRoleName(), permission.getRole().getId(), rolePath)), HttpStatus.OK);
		} else {
			response = new ResponseEntity<PermissionResponse>(new PermissionResponse("Record not found"), HttpStatus.NOT_FOUND);
		}
		return response;
	}
	
	public ResponseEntity<List<PermissionResponse>> getAllPermission() {
		List<PermissionResponse> permissionResponseList = new ArrayList<>(); 
		List<Permission> permissionList = dao.findAll();
		for(Permission permission:permissionList) {
			String rolePath = ServletUriComponentsBuilder.fromCurrentRequest().toString().split("permission")[0].concat("/role/").concat(permission.getRole().getId().toString());
			String endpointPath = ServletUriComponentsBuilder.fromCurrentRequest().toString().split("permission")[0].concat("/role/").concat(permission.getService().getId().toString());
			PermissionResponse permissionResponse = new PermissionResponse(permission.getId(), permission.getPermission(),
					ServletUriComponentsBuilder.fromCurrentRequest().path("/").path(permission.getService().getId().toString()).build().toUriString(), 
					new EndpointResponse(permission.getService().getSuffixPath(), permission.getService().getId(), permission.getService().getHttpMethod(), endpointPath), 		
					new RolesResponse(permission.getRole().getRoleName(), permission.getRole().getId(), rolePath));
			permissionResponseList.add(permissionResponse);
			
		}
		return new ResponseEntity<List<PermissionResponse>>(permissionResponseList, HttpStatus.OK);
	}
	
	private boolean isPermissionByNameAndMethodExist(Endpoint endpoint, Role role) {
		Permission permission = dao.getPermissionByServiceAndRole(endpoint, role);
		return permission!=null?true:false;
	}
	
	public ResponseEntity<PermissionResponse> getPermission(Integer roleId, Integer endpointId) {
		ResponseEntity<PermissionResponse> response;
		Optional<Endpoint> optionalEndpoint = endpointDao.findById(endpointId);
		Optional<Role> optionalRole = roleDao.findById(roleId);
		Permission permission = dao.getPermissionByServiceAndRole(optionalEndpoint.get(), optionalRole.get());
		if(permission!=null) {
			String rolePath = ServletUriComponentsBuilder.fromCurrentRequest().toString().split("permission")[0].concat("/role/").concat(permission.getRole().getId().toString());
			String endpointPath = ServletUriComponentsBuilder.fromCurrentRequest().toString().split("permission")[0].concat("/role/").concat(permission.getService().getId().toString());
			response = new ResponseEntity<PermissionResponse>(new PermissionResponse(permission.getId(), permission.getPermission(),
					ServletUriComponentsBuilder.fromCurrentRequest().path("/").path(permission.getService().getId().toString()).build().toUriString(), 
					new EndpointResponse(permission.getService().getSuffixPath(), permission.getService().getId(), permission.getService().getHttpMethod(), endpointPath), 		
					new RolesResponse(permission.getRole().getRoleName(), permission.getRole().getId(), rolePath)), HttpStatus.NO_CONTENT);
		} else {
			response = new ResponseEntity<PermissionResponse>(new PermissionResponse("Record not found"), HttpStatus.NOT_FOUND);
		}
		return response;
	}

}
