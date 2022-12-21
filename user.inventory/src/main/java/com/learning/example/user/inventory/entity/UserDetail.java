package com.learning.example.user.inventory.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the user_details database table.
 * 
 */
@Entity
@Table(name="user_details")
@NamedQuery(name="UserDetail.findAll", query="SELECT u FROM UserDetail u")
public class UserDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private Integer age;

	@Temporal(TemporalType.DATE)
	@Column(name="date_of_birth")
	private Date dateOfBirth;

	@Temporal(TemporalType.DATE)
	@Column(name="date_of_joining")
	private Date dateOfJoining;
	
	@Column(unique = true)
	private String email;

	private String name;

	private String password;

	@Column(name="phone_no")
	private Integer phoneNo;

	@Column(name="role_id")
	private Integer roleId;

	//bi-directional many-to-one association to LeaveHistory
	@OneToMany(mappedBy="userDetail1")
	private List<LeaveHistory> leaveHistories1;

	//bi-directional many-to-one association to LeaveHistory
	@OneToMany(mappedBy="approver")
	private List<LeaveHistory> leaveApprovalList;

	//bi-directional many-to-one association to LeaveManagement
	@OneToMany(mappedBy="userDetail")
	private List<LeaveManagement> leaveManagements;

	//bi-directional many-to-one association to UserAttendance
	@OneToMany(mappedBy="userDetail")
	private List<UserAttendance> userAttendances;

	//bi-directional many-to-one association to UserDetail
	@ManyToOne
	private UserDetail manager;

	//bi-directional many-to-one association to UserDetail
	@OneToMany(mappedBy="manager")
	private List<UserDetail> subOrdinates;

	//bi-directional one-to-one association to Payroll
	@OneToOne(mappedBy="userDetail")
	private Payroll payroll;

	public UserDetail() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Date getDateOfJoining() {
		return this.dateOfJoining;
	}

	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getPhoneNo() {
		return this.phoneNo;
	}

	public void setPhoneNo(Integer phoneNo) {
		this.phoneNo = phoneNo;
	}

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public List<LeaveHistory> getLeaveHistories1() {
		return this.leaveHistories1;
	}

	public void setLeaveHistories1(List<LeaveHistory> leaveHistories1) {
		this.leaveHistories1 = leaveHistories1;
	}

	public LeaveHistory addLeaveHistories1(LeaveHistory leaveHistories1) {
		getLeaveHistories1().add(leaveHistories1);
		leaveHistories1.setUserDetail1(this);

		return leaveHistories1;
	}

	public LeaveHistory removeLeaveHistories1(LeaveHistory leaveHistories1) {
		getLeaveHistories1().remove(leaveHistories1);
		leaveHistories1.setUserDetail1(null);

		return leaveHistories1;
	}

	public List<LeaveHistory> getLeaveApprovalList() {
		return this.leaveApprovalList;
	}

	public void setLeaveApprovalList(List<LeaveHistory> leaveApprovalList) {
		this.leaveApprovalList = leaveApprovalList;
	}

	public LeaveHistory addLeaveApprovalList(LeaveHistory leaveApprovalList) {
		getLeaveApprovalList().add(leaveApprovalList);
		leaveApprovalList.setApprover(this);

		return leaveApprovalList;
	}

	public LeaveHistory removeLeaveApprovalList(LeaveHistory leaveApprovalList) {
		getLeaveApprovalList().remove(leaveApprovalList);
		leaveApprovalList.setApprover(null);

		return leaveApprovalList;
	}

	public List<LeaveManagement> getLeaveManagements() {
		return this.leaveManagements;
	}

	public void setLeaveManagements(List<LeaveManagement> leaveManagements) {
		this.leaveManagements = leaveManagements;
	}

	public LeaveManagement addLeaveManagement(LeaveManagement leaveManagement) {
		getLeaveManagements().add(leaveManagement);
		leaveManagement.setUserDetail(this);

		return leaveManagement;
	}

	public LeaveManagement removeLeaveManagement(LeaveManagement leaveManagement) {
		getLeaveManagements().remove(leaveManagement);
		leaveManagement.setUserDetail(null);

		return leaveManagement;
	}

	public List<UserAttendance> getUserAttendances() {
		return this.userAttendances;
	}

	public void setUserAttendances(List<UserAttendance> userAttendances) {
		this.userAttendances = userAttendances;
	}

	public UserAttendance addUserAttendance(UserAttendance userAttendance) {
		getUserAttendances().add(userAttendance);
		userAttendance.setUserDetail(this);

		return userAttendance;
	}

	public UserAttendance removeUserAttendance(UserAttendance userAttendance) {
		getUserAttendances().remove(userAttendance);
		userAttendance.setUserDetail(null);

		return userAttendance;
	}

	public UserDetail getManager() {
		return this.manager;
	}

	public void setManager(UserDetail manager) {
		this.manager = manager;
	}

	public List<UserDetail> getSubOrdinates() {
		return this.subOrdinates;
	}

	public void setSubOrdinates(List<UserDetail> subOrdinates) {
		this.subOrdinates = subOrdinates;
	}

	public UserDetail addSubOrdinate(UserDetail subOrdinate) {
		getSubOrdinates().add(subOrdinate);
		subOrdinate.setManager(this);

		return subOrdinate;
	}

	public UserDetail removeSubOrdinate(UserDetail subOrdinate) {
		getSubOrdinates().remove(subOrdinate);
		subOrdinate.setManager(null);

		return subOrdinate;
	}

	public Payroll getPayroll() {
		return this.payroll;
	}

	public void setPayroll(Payroll payroll) {
		this.payroll = payroll;
	}

}