package com.learning.example.rbac.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the permission database table.
 * 
 */
@Entity
@NamedQuery(name="Permission.findAll", query="SELECT p FROM Permission p")
public class Permission implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	private String permission;

	//bi-directional many-to-one association to Service
	@ManyToOne(fetch=FetchType.LAZY)
	private Endpoint service;

	//bi-directional many-to-one association to Role
	@ManyToOne(fetch=FetchType.LAZY)
	private Role role;

	public Permission() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPermission() {
		return this.permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public Endpoint getService() {
		return this.service;
	}

	public void setService(Endpoint service) {
		this.service = service;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}