package com.learning.example.user.inventory.entity.response;

import com.learning.example.user.inventory.dto.UserDTO;

public class UserResponse {
	
	String message;
	
	UserDTO dto;
	

	public UserResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserResponse(String message) {
		super();
		this.message = message;
	}

	public UserResponse(String message, UserDTO dto) {
		super();
		this.message = message;
		this.dto = dto;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public UserDTO getDto() {
		return dto;
	}

	public void setDto(UserDTO dto) {
		this.dto = dto;
	}
	
	
}
