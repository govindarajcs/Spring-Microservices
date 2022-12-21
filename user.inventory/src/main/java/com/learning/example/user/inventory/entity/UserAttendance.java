package com.learning.example.user.inventory.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.Date;


/**
 * The persistent class for the user_attendance database table.
 * 
 */
@Entity
@Table(name="user_attendance")
@NamedQuery(name="UserAttendance.findAll", query="SELECT u FROM UserAttendance u")
public class UserAttendance implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@Temporal(TemporalType.DATE)
	@Column(name="attendance_date")
	private Date attendanceDate;

	@Column(name="in_time")
	private Time Integerime;

	@Column(name="out_time")
	private Time outTime;

	//bi-directional many-to-one association to UserDetail
	@ManyToOne
	@JoinColumn(name="user_id")
	private UserDetail userDetail;

	public UserAttendance() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getAttendanceDate() {
		return this.attendanceDate;
	}

	public void setAttendanceDate(Date attendanceDate) {
		this.attendanceDate = attendanceDate;
	}

	public Time getIntegerime() {
		return this.Integerime;
	}

	public void setIntegerime(Time Integerime) {
		this.Integerime = Integerime;
	}

	public Time getOutTime() {
		return this.outTime;
	}

	public void setOutTime(Time outTime) {
		this.outTime = outTime;
	}

	public UserDetail getUserDetail() {
		return this.userDetail;
	}

	public void setUserDetail(UserDetail userDetail) {
		this.userDetail = userDetail;
	}

}