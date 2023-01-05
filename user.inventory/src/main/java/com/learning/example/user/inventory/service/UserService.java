package com.learning.example.user.inventory.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

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
				ResponseEntity<RolesDTO> roleEntityResponse = roleProxy.getRoleByName(dto.getName());
				if(roleEntityResponse.getStatusCode().is2xxSuccessful()) {
					RolesDTO roleDTO = roleEntityResponse.getBody();
					
					/*
					 * convert user to user dto
					 */
					user = UserDTO.convertUserDTOToEntity(user, dto, roleDTO, userDao); 
					/*
					 * Set manager details
					 */
					
					if(dto.getManager()!=null) {
						ResponseEntity<RolesDTO> adminRole =  roleProxy.getRoleByName("Administrator");
						if(adminRole.getStatusCode().is2xxSuccessful()) {
							UserDetail adminUser = userDao.findFirstByRoleId(adminRole.getBody().getId());
							UserDetail manager = UserDTO.getManager(dto, adminUser, userDao); 
							user.setManager(manager);
							dto.setManagerDetails(UserDTO.setManagerDetails(manager, dto.getManager()));
						} else {
							System.out.println("WARNING: Manager role is empty for this user "+user.getId());
						}
					}
					/*
					 * need to write code to generate official email id
					 */
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
				ResponseEntity<RolesDTO> roleEntityResponse = roleProxy.getRoleByName(dto.getName());				
				if(roleEntityResponse.getStatusCode().is2xxSuccessful()) {
					RolesDTO roleDTO = roleEntityResponse.getBody();
					/*
					 * convert user to user dto
					 */
					user = UserDTO.convertUserDTOToEntity(user, dto, roleDTO, userDao);
					userDao.save(user);
					response = new ResponseEntity<UserDTO>(new UserDTO("User id updated successfully", dto.getName(), dto.getId(), dto.getAge(), dto.getDateOfJoining(), 
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
	
	public ResponseEntity<UserDTO> getUserById(@PathVariable("id") String id) {
		ResponseEntity<UserDTO> response = null;
		try {
			UserDTO dto = new UserDTO();
			Optional<UserDetail> userOptional = userDao.findById(id);
			if(userOptional.isPresent()) {
				UserDetail user = userOptional.get();
				ResponseEntity<RolesDTO> roleEntityResponse = roleProxy.getRoleById(user.getRoleId());
				if(roleEntityResponse.getStatusCode().is2xxSuccessful()) {
					RolesDTO roleDTO = roleEntityResponse.getBody();
					/*
					 * convert user to user dto
					 */
					dto = UserDTO.convertUserEntityToUserDTO(user, dto, roleDTO);
				}
			} else {
				response = new ResponseEntity<UserDTO>(new UserDTO("user id "+id+" doesnt exist"), HttpStatus.NOT_FOUND);
			}
			response = new ResponseEntity<UserDTO>(new UserDTO(dto.getName(), dto.getId(), dto.getAge(), dto.getDateOfJoining(), dto.getDateOfJoining(), dto.getEmail(),
					dto.getPhone(), dto.getRole(), dto.getManager(), null, null), HttpStatus.ACCEPTED);
		}
		catch(Exception e) 
		{
			response = new ResponseEntity<UserDTO>(new UserDTO("Error in retireving the details of the user"), HttpStatus.ACCEPTED);
		}
		return response;
	}
	
	public ResponseEntity<List<UserDTO>> getAllUser() {
		ResponseEntity<List<UserDTO>> response = null;
		try {
			
			List<UserDetail> userDetails = userDao.findAll();
			List<UserDTO> userDTOList = new ArrayList<UserDTO>();
			if(!userDetails.isEmpty()) {
				for(UserDetail user: userDetails) {
					ResponseEntity<RolesDTO> roleEntityResponse = roleProxy.getRoleById(user.getRoleId());
					if(roleEntityResponse.getStatusCode().is2xxSuccessful()) {
						RolesDTO roleDTO = roleEntityResponse.getBody();
						UserDTO dto = new UserDTO();
						/*
						 * convert user to user dto
						 */
						dto = UserDTO.convertUserEntityToUserDTO(user, dto, roleDTO);
						userDTOList.add(dto);
					}
				}
				
			} else {
				response = new ResponseEntity<List<UserDTO>>(HttpStatus.NOT_FOUND);
			}
			response = new ResponseEntity<List<UserDTO>>(userDTOList, HttpStatus.ACCEPTED);
		}
		catch(Exception e) 
		{
			response = new ResponseEntity<List<UserDTO>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	
	public ResponseEntity<UserDTO> deleteUser(@PathVariable("id") String id) {
		ResponseEntity<UserDTO> response = null;
		try {
			Optional<UserDetail> userOptional = userDao.findById(id);
			if(userOptional.isPresent()) {
				UserDetail user = userOptional.get();
				userDao.delete(user);
			} else {
				response = new ResponseEntity<UserDTO>(new UserDTO("user id "+id+" doesnt exist"), HttpStatus.NOT_FOUND);
			}
			response = new ResponseEntity<UserDTO>(new UserDTO("User id "+id+" deleted Successfully"), HttpStatus.ACCEPTED);
		}
		catch(Exception e) 
		{
			response = new ResponseEntity<UserDTO>(new UserDTO("Error in retireving the details of the user"), HttpStatus.ACCEPTED);
		}
		return response;
	}
	
}
