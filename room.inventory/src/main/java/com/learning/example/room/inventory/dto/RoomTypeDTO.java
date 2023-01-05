package com.learning.example.room.inventory.dto;

import java.util.List;

import com.learning.example.room.inventory.entity.RoomType;

public class RoomTypeDTO {
	
	String message;
	Integer id; 
	Integer numOfBeds;
	String name;
	Integer price;
	String ACType;
	String href;
	
	List<RoomTypeDTO> dtoTypeList;
	
	public RoomTypeDTO(List<RoomTypeDTO> dtoTypeList) {
		this.dtoTypeList = dtoTypeList;
	}
	
	public RoomTypeDTO(String message, Integer id, Integer numOfBeds, String name, Integer price, String ACType) {
		this.message = message;
		this.id = id;
		this.name = name;
		this.numOfBeds = numOfBeds;
		this.price = price;
		this.ACType = ACType;
	}
	
	public RoomTypeDTO(String message) {
		this.message = message;
	}
	
	public RoomTypeDTO(Integer id, Integer numOfBeds, String name, Integer price, String ACType) {
		this.id = id;
		this.name = name;
		this.numOfBeds = numOfBeds;
		this.price = price;
		this.ACType = ACType;
	}
	
	public RoomTypeDTO() {
		// TODO Auto-generated constructor stub
	}

	public String getACType() {
		return ACType;
	}

	public void setACType(String aCType) {
		ACType = aCType;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getNumOfBeds() {
		return numOfBeds;
	}
	public void setNumOfBeds(Integer numOfBeds) {
		this.numOfBeds = numOfBeds;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public List<RoomTypeDTO> getDtoTypeList() {
		return dtoTypeList;
	}

	public void setDtoTypeList(List<RoomTypeDTO> dtoTypeList) {
		this.dtoTypeList = dtoTypeList;
	}

	static public RoomTypeDTO convertEntityToDTO(RoomType type) {
		RoomTypeDTO dto = new RoomTypeDTO();
		dto.setACType(type.getAcType());
		dto.setId(type.getId());
		dto.setName(type.getName());
		dto.setNumOfBeds(type.getNumOfBeds());
		dto.setPrice(type.getPrice());
		return dto;
	}
	
	
	static public RoomType convertDtoToEntity(RoomTypeDTO dto) {
		RoomType type = new RoomType();
		type.setAcType(dto.getACType());
		type.setName(dto.getName());
		type.setNumOfBeds(dto.getNumOfBeds());
		type.setPrice(dto.getPrice());
		return type;
	}
	
	
	
}
