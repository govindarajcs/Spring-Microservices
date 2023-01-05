package com.learning.example.room.inventory.dto;

import java.util.List;

import com.learning.example.room.inventory.entity.RoomDetail;
import com.learning.example.room.inventory.entity.RoomType;

public class RoomDetailDTO {
	
	Integer roomNo;
	String message;
	String link;
	Integer roomType;
	RoomTypeDTO roomTypeDTO;

	List<RoomDetailDTO> roomDetailDTOList;
	
	public RoomDetailDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RoomDetailDTO(Integer roomNo, String message, String link, Integer roomType) {
		super();
		this.roomNo = roomNo;
		this.message = message;
		this.link = link;
		this.roomType = roomType;
	}
	
	public RoomDetailDTO(String message, List<RoomDetailDTO> roomDetailDTOList) {
		super();
		this.message = message;
		this.roomDetailDTOList = roomDetailDTOList;
	}


	public RoomDetailDTO(String message) {
		super();
		this.message = message;
	}
	
	public RoomTypeDTO getRoomTypeDTO() {
		return roomTypeDTO;
	}

	public void setRoomTypeDTO(RoomTypeDTO roomTypeDTO) {
		this.roomTypeDTO = roomTypeDTO;
	}
	
	
	public List<RoomDetailDTO> getRoomDetailDTOList() {
		return roomDetailDTOList;
	}

	public void setRoomDetailDTOList(List<RoomDetailDTO> roomDetailDTOList) {
		this.roomDetailDTOList = roomDetailDTOList;
	}


	public Integer getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(Integer roomNo) {
		this.roomNo = roomNo;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public Integer getRoomType() {
		return roomType;
	}
	public void setRoomType(Integer roomType) {
		this.roomType = roomType;
	}
	
	static public RoomDetailDTO convertEntityToDTO(RoomDetail detail, RoomTypeDTO typeDTO) {
		RoomDetailDTO dto = new RoomDetailDTO();
		dto.setRoomNo(detail.getRoomNo());
		dto.setRoomTypeDTO(typeDTO);
		return dto;
	}
	
	
	static public RoomDetail convertDtoToEntity(RoomDetailDTO dto, RoomType type) {
		RoomDetail roomDetail = new RoomDetail();
		roomDetail.setRoomNo(dto.getRoomNo());
		if(type!=null)
			roomDetail.setRoomTypeBean(type);
		return roomDetail;
	}
	

}
