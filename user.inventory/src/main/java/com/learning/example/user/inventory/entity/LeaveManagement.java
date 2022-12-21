package com.learning.example.user.inventory.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the leave_management database table.
 * 
 */
@Entity
@Table(name="leave_management")
@NamedQuery(name="LeaveManagement.findAll", query="SELECT l FROM LeaveManagement l")
public class LeaveManagement implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private Integer availed;

	private Integer balance;

	private Integer pending;

	//bi-directional many-to-one association to LeaveType
	@ManyToOne
	@JoinColumn(name="leave_type")
	private LeaveType leaveType;

	//bi-directional many-to-one association to UserDetail
	@ManyToOne
	@JoinColumn(name="user_id")
	private UserDetail userDetail;

	public LeaveManagement() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAvailed() {
		return this.availed;
	}

	public void setAvailed(Integer availed) {
		this.availed = availed;
	}

	public Integer getBalance() {
		return this.balance;
	}

	public void setBalance(Integer balance) {
		this.balance = balance;
	}

	public Integer getPending() {
		return this.pending;
	}

	public void setPending(Integer pending) {
		this.pending = pending;
	}

	public LeaveType getLeaveType() {
		return this.leaveType;
	}

	public void setLeaveType(LeaveType leaveType) {
		this.leaveType = leaveType;
	}

	public UserDetail getUserDetail() {
		return this.userDetail;
	}

	public void setUserDetail(UserDetail userDetail) {
		this.userDetail = userDetail;
	}

}