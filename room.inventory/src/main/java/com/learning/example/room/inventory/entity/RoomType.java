package com.learning.example.room.inventory.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the room_type database table.
 * 
 */
@Entity
@Table(name="room_type")
@NamedQuery(name="RoomType.findAll", query="SELECT r FROM RoomType r")
public class RoomType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="ac_type")
	private String acType;

	private String name;

	@Column(name="num_of_beds")
	private int numOfBeds;

	private int price;

	//bi-directional many-to-one association to RoomDetail
	@OneToMany(mappedBy="roomTypeBean")
	private List<RoomDetail> roomDetails;

	public RoomType() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAcType() {
		return this.acType;
	}

	public void setAcType(String acType) {
		this.acType = acType;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumOfBeds() {
		return this.numOfBeds;
	}

	public void setNumOfBeds(int numOfBeds) {
		this.numOfBeds = numOfBeds;
	}

	public int getPrice() {
		return this.price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public List<RoomDetail> getRoomDetails() {
		return this.roomDetails;
	}

	public void setRoomDetails(List<RoomDetail> roomDetails) {
		this.roomDetails = roomDetails;
	}

	public RoomDetail addRoomDetail(RoomDetail roomDetail) {
		getRoomDetails().add(roomDetail);
		roomDetail.setRoomTypeBean(this);

		return roomDetail;
	}

	public RoomDetail removeRoomDetail(RoomDetail roomDetail) {
		getRoomDetails().remove(roomDetail);
		roomDetail.setRoomTypeBean(null);

		return roomDetail;
	}

}