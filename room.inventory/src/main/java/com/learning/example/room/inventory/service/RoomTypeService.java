package com.learning.example.room.inventory.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.learning.example.room.inventory.dto.RoomTypeDTO;
import com.learning.example.room.inventory.entity.RoomType;
import com.learning.example.room.inventory.repository.RoomTypeRepository;

@Service
public class RoomTypeService {

	@Autowired
	RoomTypeRepository repository;

	public ResponseEntity<RoomTypeDTO> createRoomType(RoomTypeDTO dto) {
		ResponseEntity<RoomTypeDTO> response;
		try {
			RoomType type = RoomTypeDTO.convertDtoToEntity(dto);
			Optional<RoomType> roomTypeOptional	= repository.findByNameAndAcType(dto.getName(), dto.getACType());
			if(roomTypeOptional.isEmpty()) {
				RoomType updatedRoomType = repository.save(type);
				dto.setId(updatedRoomType.getId());
				dto.setMessage("Roomtype created successfully");
				response = new ResponseEntity<RoomTypeDTO>(dto,HttpStatus.CREATED);
			} else {
				response = new ResponseEntity<RoomTypeDTO>(new RoomTypeDTO("Room type "+dto.getName()+" already exist"), HttpStatus.BAD_REQUEST); 
			}
		} catch(Exception e) {
			response = new ResponseEntity<RoomTypeDTO>(new RoomTypeDTO("Error in creating the roomType. Error:"+e.getLocalizedMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	public ResponseEntity<RoomTypeDTO> updateRoomType(RoomTypeDTO dto) {
		ResponseEntity<RoomTypeDTO> response;
		try {
			RoomType type = RoomTypeDTO.convertDtoToEntity(dto);
			Optional<RoomType> roomTypeOptional = repository.findById(dto.getId());
			if(roomTypeOptional.isPresent()) {
				RoomType updatedRoomType = repository.save(type);
				dto.setId(updatedRoomType.getId());
				dto.setMessage("Roomtype details updated successfully");
				response = new ResponseEntity<RoomTypeDTO>(dto,HttpStatus.CREATED);
			} else {
				response = new ResponseEntity<RoomTypeDTO>(new RoomTypeDTO("Room type "+dto.getId()+" doesn't exist"), HttpStatus.BAD_REQUEST); 
			}
		} catch(Exception e) {
			response = new ResponseEntity<RoomTypeDTO>(new RoomTypeDTO("Error in updating the roomType. Error:"+e.getLocalizedMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	public ResponseEntity<RoomTypeDTO> deleteRoomType(Integer id) {
		ResponseEntity<RoomTypeDTO> response;
		try {

			Optional<RoomType> roomTypeOptional	= repository.findById(id);
			if(roomTypeOptional.isPresent()) {
				repository.delete(roomTypeOptional.get());
				response = new ResponseEntity<RoomTypeDTO>(new RoomTypeDTO(), HttpStatus.NO_CONTENT);
			} else {
				response = new ResponseEntity<RoomTypeDTO>(new RoomTypeDTO("Room type "+id+" doesn't exist"), HttpStatus.NOT_FOUND); 
			}
		} catch(Exception e) {
			response = new ResponseEntity<RoomTypeDTO>(new RoomTypeDTO("Error in deleting the roomType "+id+". Error:"+e.getLocalizedMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	public ResponseEntity<RoomTypeDTO> getRoomTypeById(Integer id) {
		ResponseEntity<RoomTypeDTO> response;
		try {

			Optional<RoomType> roomTypeOptional	= repository.findById(id);
			if(roomTypeOptional.isPresent()) {
				RoomType roomType =	roomTypeOptional.get();
				RoomTypeDTO dto = RoomTypeDTO.convertEntityToDTO(roomType);
				String link = ServletUriComponentsBuilder.fromCurrentRequest().path("/").path(id.toString()).build().toString();
				dto.setHref(link);
				response = new ResponseEntity<RoomTypeDTO>(dto, HttpStatus.NO_CONTENT);
			} else {
				response = new ResponseEntity<RoomTypeDTO>(new RoomTypeDTO("Room type "+id+" doesn't exist"), HttpStatus.NOT_FOUND); 
			}
		} catch(Exception e) {
			response = new ResponseEntity<RoomTypeDTO>(new RoomTypeDTO("Error in retrieving the roomType "+id+". Error:"+e.getLocalizedMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	public ResponseEntity<RoomTypeDTO> getAllRoomType() {
		ResponseEntity<RoomTypeDTO> response;
		try {

			List<RoomType> roomTypeList	= repository.findAll();
			if(!roomTypeList.isEmpty()) {

				List<RoomTypeDTO> dtoList = new ArrayList<RoomTypeDTO>();
				for(RoomType type:roomTypeList) {
					RoomTypeDTO dto = RoomTypeDTO.convertEntityToDTO(type);
					String link = ServletUriComponentsBuilder.fromCurrentRequest().path("/").path(dto.getId().toString()).build().toString();
					dto.setHref(link);
					dtoList.add(dto);
				}
				response = new ResponseEntity<RoomTypeDTO>(new RoomTypeDTO(dtoList), HttpStatus.ACCEPTED);
			} else {
				response = new ResponseEntity<RoomTypeDTO>(new RoomTypeDTO("No Room types defined"), HttpStatus.NOT_FOUND); 
			}
		} catch(Exception e) {
			response = new ResponseEntity<RoomTypeDTO>(new RoomTypeDTO("Error in retrieving the roomTypes. Error:"+e.getLocalizedMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

}
