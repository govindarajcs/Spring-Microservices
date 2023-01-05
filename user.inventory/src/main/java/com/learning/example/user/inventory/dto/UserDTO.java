package com.learning.example.user.inventory.dto;

import java.util.Date;
import java.util.Optional;
import java.util.Random;

import com.learning.example.user.inventory.dao.UserRepository;
import com.learning.example.user.inventory.entity.UserDetail;

public class UserDTO {
	
	String message;
	String name;
	String id;
	Integer age;
	Date dateOfJoining;
	Date dateOfBirth;
	String email;
	String phone;
	String role;
	String manager;
	RolesDTO roleDetails;
	UserDTO managerDetails;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Date getDateOfJoining() {
		return dateOfJoining;
	}
	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	public RolesDTO getRoleDetails() {
		return roleDetails;
	}
	public void setRoleDetails(RolesDTO roleDetails) {
		this.roleDetails = roleDetails;
	}
	public UserDTO getManagerDetails() {
		return managerDetails;
	}
	public void setManagerDetails(UserDTO managerDetails) {
		this.managerDetails = managerDetails;
	}
	public UserDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserDTO(String message, String name, String id, Integer age, Date dateOfJoining, Date dateOfBirth,
			String email, String phone, String role, String manager) {
		super();
		this.message = message;
		this.name = name;
		this.id = id;
		this.age = age;
		this.dateOfJoining = dateOfJoining;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.phone = phone;
		this.role = role;
		this.manager = manager;
	
	}
	public UserDTO(String name, String id, Integer age, Date dateOfJoining, Date dateOfBirth, String email,
			String phone, String role, String manager, RolesDTO roleDetails, UserDTO managerDetails) {
		super();
		this.name = name;
		this.id = id;
		this.age = age;
		this.dateOfJoining = dateOfJoining;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.phone = phone;
		this.role = role;
		this.manager = manager;
		this.roleDetails = roleDetails;
		this.managerDetails = managerDetails;
	}
	public UserDTO(String message) {
		super();
		this.message = message;
	}
	
	
	static public UserDTO convertUserEntityToUserDTO(UserDetail user, UserDTO dto, RolesDTO roleDTO) {
		dto.setAge(user.getAge());
		dto.setDateOfBirth(user.getDateOfBirth());
		dto.setDateOfJoining(user.getDateOfJoining());
		dto.setEmail(user.getEmail());
		dto.setId(user.getId());
		if (user.getManager()!=null) {
			dto.setManager(user.getManager().getId());
		}
		dto.setName(user.getName());
		dto.setPhone(user.getPhoneNo());
		dto.setRole(roleDTO.getName());
		return dto;
	}
	
	static public UserDetail convertUserDTOToEntity(UserDetail user, UserDTO dto, RolesDTO roleDTO, UserRepository userDao) {
		
		if(dto.getAge()!=null) {
			user.setAge(dto.getAge());
		}
		if(dto.getDateOfBirth()!=null) {
			user.setDateOfBirth(dto.getDateOfBirth());
		}
		if(dto.getDateOfJoining()!=null) {
			user.setDateOfJoining(dto.getDateOfJoining());
		} 
		if(dto.getEmail()!=null) {
			user.setEmail(dto.getEmail());
		}
		if(dto.getPhone()!=null) {
			user.setPhoneNo(dto.getPhone());
		}
		if(dto.getName()!=null) {
			user.setName(dto.getName());
		}
		
		/*
		 * set the role to the user
		 */
		if(dto.getRole()!=null) {
			user.setRoleId(roleDTO.getId());
			dto.setRoleDetails(roleDTO);
		}
		
		
		/*
		 * Set userId
		 */
		if(dto.getId() == null) {
			user.setId(UserDTO.getUserId(dto));
		}
		else {
			user.setId(dto.getId());
		}
		return user;
	}
	
	public static UserDTO setManagerDetails(UserDetail manager, String managerName) {
		UserDTO userDTO = new UserDTO();
		if(manager!=null) {
			userDTO.setAge(manager.getAge());
			userDTO.setName(manager.getName());
				
		} else {
			userDTO.setName(managerName);
		}
		return userDTO;
	}
	
	static public String getUserId(UserDTO dto) {
		String userId = new StringBuffer(dto.getRole().substring(0, 3).toUpperCase())
				.append(String.valueOf(dto.getName().length()))
				.append(dto.getName().charAt(1))
				.append(Integer.toString(new Random().nextInt(10)))
				.append(Integer.toString(new Random().nextInt(20))).toString();
		return userId;
		
	}
	
	static public UserDetail getManager(UserDTO dto, UserDetail adminUser, UserRepository userDao) {
		UserDetail manager = null;
		if (dto.getRole().equals("Employee")) {
			Optional<UserDetail> managerOptional = userDao.findById(dto.getManager());
			manager = managerOptional.isEmpty()?adminUser:managerOptional.get();
		} else if(dto.getRole().equals("Customer") || dto.getRole().equals("Administrator")) {
			manager = adminUser;
		}
		return manager;
	}
	
}
