package com.learning.example.room.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.example.room.inventory.entity.RoomDetail;

public interface RoomRepository extends JpaRepository<RoomDetail, Integer> {

}
