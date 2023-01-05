package com.learning.example.room.inventory.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.example.room.inventory.entity.RoomType;

public interface RoomTypeRepository extends JpaRepository<RoomType, Integer> {
	
	public Optional<RoomType> findByNameAndAcType(String name, String type);

}
