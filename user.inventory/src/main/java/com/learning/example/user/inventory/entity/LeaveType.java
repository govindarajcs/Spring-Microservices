package com.learning.example.user.inventory.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the leave_type database table.
 * 
 */
@Entity
@Table(name="leave_type")
@NamedQuery(name="LeaveType.findAll", query="SELECT l FROM LeaveType l")
public class LeaveType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String description;

	@Column(name="leave_name")
	private String leaveName;

	private Integer total;

	//bi-directional many-to-one association to LeaveHistory
	@OneToMany(mappedBy="leaveTypeBean")
	private List<LeaveHistory> leaveHistories;

	//bi-directional many-to-one association to LeaveManagement
	@OneToMany(mappedBy="leaveType")
	private List<LeaveManagement> leaveManagements;

	public LeaveType() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLeaveName() {
		return this.leaveName;
	}

	public void setLeaveName(String leaveName) {
		this.leaveName = leaveName;
	}

	public Integer getTotal() {
		return this.total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<LeaveHistory> getLeaveHistories() {
		return this.leaveHistories;
	}

	public void setLeaveHistories(List<LeaveHistory> leaveHistories) {
		this.leaveHistories = leaveHistories;
	}

	public LeaveHistory addLeaveHistory(LeaveHistory leaveHistory) {
		getLeaveHistories().add(leaveHistory);
		leaveHistory.setLeaveTypeBean(this);

		return leaveHistory;
	}

	public LeaveHistory removeLeaveHistory(LeaveHistory leaveHistory) {
		getLeaveHistories().remove(leaveHistory);
		leaveHistory.setLeaveTypeBean(null);

		return leaveHistory;
	}

	public List<LeaveManagement> getLeaveManagements() {
		return this.leaveManagements;
	}

	public void setLeaveManagements(List<LeaveManagement> leaveManagements) {
		this.leaveManagements = leaveManagements;
	}

	public LeaveManagement addLeaveManagement(LeaveManagement leaveManagement) {
		getLeaveManagements().add(leaveManagement);
		leaveManagement.setLeaveType(this);

		return leaveManagement;
	}

	public LeaveManagement removeLeaveManagement(LeaveManagement leaveManagement) {
		getLeaveManagements().remove(leaveManagement);
		leaveManagement.setLeaveType(null);

		return leaveManagement;
	}

}