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

import com.learning.example.room.inventory.dto.RoomDetailDTO;
import com.learning.example.room.inventory.service.RoomService;

@RequestMapping(path = "/room", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class RoomController {
	
	@Autowired
	RoomService service;
	
	@PostMapping
	public ResponseEntity<RoomDetailDTO> createRoomType(@RequestBody RoomDetailDTO dto) {
		return service.addRoom(dto);
	}
	
	@PutMapping
	public ResponseEntity<RoomDetailDTO> updateRoomType(@RequestBody RoomDetailDTO dto) {
		return service.updateRoom(dto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<RoomDetailDTO> deleteRoomType(@PathVariable("id") Integer id) {
		return service.deleteRoom(id);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<RoomDetailDTO> getRoomTypeDetail(@PathVariable("id") Integer id) {
		return service.getRoomDetailById(id);
	}
	
	@GetMapping
	public ResponseEntity<RoomDetailDTO> getAllRoomTypes() {
		return service.getAllRooms();
	}
}
