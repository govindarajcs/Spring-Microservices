package com.learning.example.room.inventory.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.learning.example.room.inventory.dto.RoomDetailDTO;
import com.learning.example.room.inventory.dto.RoomTypeDTO;
import com.learning.example.room.inventory.entity.RoomDetail;
import com.learning.example.room.inventory.entity.RoomType;
import com.learning.example.room.inventory.repository.RoomRepository;
import com.learning.example.room.inventory.repository.RoomTypeRepository;

@Service
public class RoomService {

	@Autowired
	RoomRepository repository;

	@Autowired
	RoomTypeRepository roomTypeRepository;

	public ResponseEntity<RoomDetailDTO> addRoom(RoomDetailDTO dto) {
		ResponseEntity<RoomDetailDTO> response;
		try {
			Optional<RoomDetail> roomDetailOptional = repository.findById(dto.getRoomNo());
			if(roomDetailOptional.isPresent()) {
				response = new ResponseEntity<RoomDetailDTO>(new RoomDetailDTO("Room details for the id "+dto.getRoomNo()+" already present "), HttpStatus.BAD_REQUEST);
			} else {
				Optional<RoomType> roomTypeOptional = roomTypeRepository.findById(dto.getRoomType());
				if(roomTypeOptional.isPresent()) {
					RoomDetail roomDetail = RoomDetailDTO.convertDtoToEntity(dto, roomTypeOptional.get());
					repository.save(roomDetail);
					dto.setMessage("Room details of "+dto.getRoomNo()+" got added successfully");
					response = new ResponseEntity<RoomDetailDTO>(dto, HttpStatus.CREATED);
				} else {
					response = new ResponseEntity<RoomDetailDTO>(new RoomDetailDTO("Room type id "+dto.getRoomType()+" doesn't exist"), HttpStatus.BAD_REQUEST);
				}
			}
		} catch(Exception e) {
			response = new ResponseEntity<RoomDetailDTO>(new RoomDetailDTO("Error in adding the room details"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	public ResponseEntity<RoomDetailDTO> updateRoom(RoomDetailDTO dto) {
		ResponseEntity<RoomDetailDTO> response;
		try {
			Optional<RoomDetail> roomDetailOptional = repository.findById(dto.getRoomNo());
			if(roomDetailOptional.isPresent()) {
				Optional<RoomType> roomTypeOptional = null;
				if(dto.getRoomType()!=null) {
					roomTypeOptional = roomTypeRepository.findById(dto.getRoomType());
				}
				if(roomTypeOptional!=null && roomTypeOptional.isPresent()) {
					RoomDetail roomDetail = RoomDetailDTO.convertDtoToEntity(dto, roomTypeOptional.get());
					repository.save(roomDetail);
					dto.setMessage("Room details of "+dto.getRoomNo()+" got added successfully");
					response = new ResponseEntity<RoomDetailDTO>(dto, HttpStatus.CREATED);
				} else {
					response = new ResponseEntity<RoomDetailDTO>(new RoomDetailDTO("Room type id "+dto.getRoomType()+" doesn't exist"), HttpStatus.BAD_REQUEST);
				}
			} else {
				response = new ResponseEntity<RoomDetailDTO>(new RoomDetailDTO("Room details for the id "+dto.getRoomNo()+" doesn't exist"), HttpStatus.BAD_REQUEST);
			}
		} catch(Exception e) {
			response = new ResponseEntity<RoomDetailDTO>(new RoomDetailDTO("Error in adding the room details"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	
	public ResponseEntity<RoomDetailDTO> deleteRoom(Integer id) {
		ResponseEntity<RoomDetailDTO> response;
		try {
			Optional<RoomDetail> roomDetailOptional = repository.findById(id);
			if(roomDetailOptional.isPresent()) {
				repository.delete(roomDetailOptional.get());
				response = new ResponseEntity<RoomDetailDTO>(new RoomDetailDTO(), HttpStatus.NO_CONTENT);
			} else {
				response = new ResponseEntity<RoomDetailDTO>(new RoomDetailDTO("Room details for the id "+id+" doesn't exist"), HttpStatus.BAD_REQUEST);
			}
		} catch(Exception e) {
			response = new ResponseEntity<RoomDetailDTO>(new RoomDetailDTO("Error in adding the room details"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	
	public ResponseEntity<RoomDetailDTO> getRoomDetailById(Integer id) {
		ResponseEntity<RoomDetailDTO> response;
		try {
			Optional<RoomDetail> roomDetailOptional = repository.findById(id);
			if(roomDetailOptional.isPresent()) {
				RoomDetail roomDetail = roomDetailOptional.get();
				RoomType roomType = roomDetail.getRoomTypeBean();
				RoomTypeDTO typeDTO = RoomTypeDTO.convertEntityToDTO(roomType);
				RoomDetailDTO dto = RoomDetailDTO.convertEntityToDTO(roomDetail, typeDTO);
				dto.setLink(ServletUriComponentsBuilder.fromCurrentRequest().path("/").path(id.toString()).build().toString());
				response = new ResponseEntity<RoomDetailDTO>(dto, HttpStatus.ACCEPTED);
			} else {
				response = new ResponseEntity<RoomDetailDTO>(new RoomDetailDTO("Room details for the id "+id+" doesn't exist"), HttpStatus.NOT_FOUND);
			}
		} catch(Exception e) {
			response = new ResponseEntity<RoomDetailDTO>(new RoomDetailDTO("Error in adding the room details"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	
	
	public ResponseEntity<RoomDetailDTO> getAllRooms() {
		ResponseEntity<RoomDetailDTO> response;
		try {
			List<RoomDetail> roomDetailsList = repository.findAll();
			if(!roomDetailsList.isEmpty()) {
				List<RoomDetailDTO> dtoList = new ArrayList<RoomDetailDTO>();
				for(RoomDetail roomDetail : roomDetailsList) {
					RoomType roomType = roomDetail.getRoomTypeBean();
					RoomTypeDTO typeDTO = RoomTypeDTO.convertEntityToDTO(roomType);
					RoomDetailDTO dto = RoomDetailDTO.convertEntityToDTO(roomDetail, typeDTO);
					dto.setLink(ServletUriComponentsBuilder.fromCurrentRequest().path("/").path(dto.getRoomNo().toString()).build().toString());
					dtoList.add(dto);
				}
				response = new ResponseEntity<RoomDetailDTO>(new RoomDetailDTO("Retrieved the list of rooms", dtoList), HttpStatus.ACCEPTED);
			} else {
				response = new ResponseEntity<RoomDetailDTO>(new RoomDetailDTO("No Rooms Available"), HttpStatus.NOT_FOUND);
			}
		} catch(Exception e) {
			response = new ResponseEntity<RoomDetailDTO>(new RoomDetailDTO("Error in adding the room details"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	

}
