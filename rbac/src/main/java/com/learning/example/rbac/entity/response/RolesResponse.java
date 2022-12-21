package com.learning.example.rbac.entity.response;

public class RolesResponse {

	String message;
	String name;
	Integer id;
	String link;
	
	public RolesResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public RolesResponse(String message, String name, Integer id, String link) {
		super();
		this.message = message;
		this.name = name;
		this.id = id;
		this.link = link;
	}
	
	public RolesResponse(String name, Integer id, String link) {
		super();
		this.name = name;
		this.id = id;
		this.link = link;
	}

	public RolesResponse(String message) {
		super();
		this.message = message;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
