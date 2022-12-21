package com.learning.example.user.inventory.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the leave_history database table.
 * 
 */
@Entity
@Table(name="leave_history")
@NamedQuery(name="LeaveHistory.findAll", query="SELECT l FROM LeaveHistory l")
public class LeaveHistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@Temporal(TemporalType.DATE)
	@Column(name="from_date")
	private Date fromDate;

	@Column(name="leave_status")
	private String leaveStatus;

	@Temporal(TemporalType.DATE)
	@Column(name="to_date")
	private Date toDate;

	//bi-directional many-to-one association to UserDetail
	@ManyToOne
	@JoinColumn(name="approver")
	private UserDetail userDetail1;

	//bi-directional many-to-one association to LeaveType
	@ManyToOne
	@JoinColumn(name="leave_type")
	private LeaveType leaveTypeBean;

	//bi-directional many-to-one association to UserDetail
	@ManyToOne
	@JoinColumn(name="user_id")
	private UserDetail approver;

	public LeaveHistory() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFromDate() {
		return this.fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public String getLeaveStatus() {
		return this.leaveStatus;
	}

	public void setLeaveStatus(String leaveStatus) {
		this.leaveStatus = leaveStatus;
	}

	public Date getToDate() {
		return this.toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public UserDetail getUserDetail1() {
		return this.userDetail1;
	}

	public void setUserDetail1(UserDetail userDetail1) {
		this.userDetail1 = userDetail1;
	}

	public LeaveType getLeaveTypeBean() {
		return this.leaveTypeBean;
	}

	public void setLeaveTypeBean(LeaveType leaveTypeBean) {
		this.leaveTypeBean = leaveTypeBean;
	}

	public UserDetail getApprover() {
		return this.approver;
	}

	public void setApprover(UserDetail approver) {
		this.approver = approver;
	}

}