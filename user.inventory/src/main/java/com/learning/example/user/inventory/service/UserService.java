package com.learning.example.user.inventory.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.learning.example.user.inventory.dao.UserRepository;
import com.learning.example.user.inventory.dto.RolesDTO;
import com.learning.example.user.inventory.dto.UserDTO;
import com.learning.example.user.inventory.entity.UserDetail;
import com.learning.example.user.inventory.proxy.RoleProxy;

@Service
public class UserService {

	@Autowired
	RoleProxy roleProxy;

	@Autowired
	UserRepository userDao;


	public ResponseEntity<UserDTO> createUser(UserDTO dto) {
		ResponseEntity<UserDTO> response = null;
		try {
			UserDetail user = new UserDetail();

			/**
			 * If the user is a employee then another employee is their manager who can also be an administrator.
			 * If the user is a administrator then another administrator will be their manager.
			 */
			if (userDao.findByEmail(dto.getEmail()).isEmpty() && userDao.findByPhoneNo(dto.getPhone()).isEmpty() ) {
				ResponseEntity<List<RolesDTO>> roleEntityResponse = roleProxy.getAllRoles();
				Map<String, Integer> rolesMap = null;
				if(roleEntityResponse.getStatusCode().is2xxSuccessful()) {
					List<RolesDTO> rolesList = roleEntityResponse.getBody();
					rolesMap = rolesList.stream().collect(Collectors.toMap(RolesDTO::getName,RolesDTO::getId));
					/*
					 * convert user to user dto
					 */
					user = UserDTO.convertUserDTOToEntity(user, dto, rolesMap, userDao); 

					UserDetail updatedUser = userDao.save(user);
					if (updatedUser != null) {
						response = new ResponseEntity<UserDTO>(new UserDTO("User id created successfully", dto.getName(), dto.getId(), dto.getAge(), dto.getDateOfJoining(), 
								dto.getDateOfBirth(), dto.getEmail(), dto.getPhone(), dto.getRoleDetails().getName(), dto.getManagerDetails().getName()), 
								HttpStatus.ACCEPTED);
					} else {
						response = new ResponseEntity<UserDTO>(new UserDTO("Error in fetching the role details"), HttpStatus.INTERNAL_SERVER_ERROR);
					}
				} else {
					response = new ResponseEntity<UserDTO>(new UserDTO("Error in fetching the role details"), roleEntityResponse.getStatusCode());
				}
			} else {
				response = new ResponseEntity<UserDTO>(new UserDTO("User record with emailId "+dto.getEmail()+" or phone no "+dto.getPhone()+" already exist "), HttpStatus.BAD_REQUEST);
			}
		} catch(Exception e) {
			response = new ResponseEntity<UserDTO>(new UserDTO("Error in creating the user record "+dto.getName()+ "due to "+e.getLocalizedMessage()), 
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	
	public ResponseEntity<UserDTO> updateUser(UserDTO dto) {
		ResponseEntity<UserDTO> response = null;
		try {
		Optional<UserDetail> userOptional = userDao.findById(dto.getId());
		if(userOptional.isPresent()) {
			UserDetail user = userOptional.get();
			ResponseEntity<List<RolesDTO>> roleEntityResponse = roleProxy.getAllRoles();
			Map<String, Integer> rolesMap = null;
			if(roleEntityResponse.getStatusCode().is2xxSuccessful()) {
				List<RolesDTO> rolesList = roleEntityResponse.getBody();
				rolesMap = rolesList.stream().collect(Collectors.toMap(RolesDTO::getName,RolesDTO::getId));
				/*
				 * convert user to user dto
				 */
				user = UserDTO.convertUserDTOToEntity(user, dto, rolesMap, userDao);
				userDao.save(user);
				response = new ResponseEntity<UserDTO>(new UserDTO("User id created successfully", dto.getName(), dto.getId(), dto.getAge(), dto.getDateOfJoining(), 
						dto.getDateOfBirth(), dto.getEmail(), dto.getPhone(), dto.getRoleDetails().getName(), dto.getManagerDetails().getName()), 
						HttpStatus.ACCEPTED);
			} else {
				response = new ResponseEntity<UserDTO>(new UserDTO("Error in retireving role details"), HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
		} else {
			response = new ResponseEntity<UserDTO>(new UserDTO("User with id "+dto.getId()+" doesn't exist"), HttpStatus.NOT_FOUND);
		}
		} catch (Exception e) {
			response = new ResponseEntity<UserDTO>(new UserDTO("Error in updating the user record "+dto.getId()), HttpStatus.NOT_FOUND);
		}
		return response;
	}
	
}
