package com.learning.example.room.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.example.room.inventory.dto.RoomTypeDTO;
import com.learning.example.room.inventory.service.RoomTypeService;

@RequestMapping(path = "/roomtype", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class RoomTypeController {
	
	@Autowired
	RoomTypeService service;
	
	@PostMapping
	public ResponseEntity<RoomTypeDTO> createRoomType(@RequestBody RoomTypeDTO dto) {
		return service.createRoomType(dto);
	}
	
	@PutMapping
	public ResponseEntity<RoomTypeDTO> updateRoomType(@RequestBody RoomTypeDTO dto) {
		return service.updateRoomType(dto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<RoomTypeDTO> deleteRoomType(@PathVariable("id") Integer id) {
		return service.deleteRoomType(id);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<RoomTypeDTO> getRoomTypeDetail(@PathVariable("id") Integer id) {
		return service.getRoomTypeById(id);
	}
	
	@GetMapping
	public ResponseEntity<RoomTypeDTO> getAllRoomTypes() {
		return service.getAllRoomType();
	}

}
