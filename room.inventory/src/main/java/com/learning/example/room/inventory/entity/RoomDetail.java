package com.learning.example.room.inventory.entity;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the room_details database table.
 * 
 */
@Entity
@Table(name="room_details")
@NamedQuery(name="RoomDetail.findAll", query="SELECT r FROM RoomDetail r")
public class RoomDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="room_no")
	private int roomNo;

	//bi-directional many-to-one association to RoomType
	@ManyToOne
	@JoinColumn(name="room_type")
	private RoomType roomTypeBean;

	public RoomDetail() {
	}

	public int getRoomNo() {
		return this.roomNo;
	}

	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}

	public RoomType getRoomTypeBean() {
		return this.roomTypeBean;
	}

	public void setRoomTypeBean(RoomType roomTypeBean) {
		this.roomTypeBean = roomTypeBean;
	}

}