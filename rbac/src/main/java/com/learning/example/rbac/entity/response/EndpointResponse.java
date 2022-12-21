package com.learning.example.rbac.entity.response;

public class EndpointResponse {
	
	String message;
	String endpointName;
	Integer id;
	String httpMethod;
	String link;
	
	
	
	public EndpointResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EndpointResponse(String message) {
		super();
		this.message = message;
	}
	public EndpointResponse(String message, String endpointName, Integer id, String httpMethod, String link) {
		super();
		this.message = message;
		this.endpointName = endpointName;
		this.id = id;
		this.httpMethod = httpMethod;
		this.link = link;
	}
	
	public EndpointResponse(String endpointName, Integer id, String httpMethod, String link) {
		super();
		this.endpointName = endpointName;
		this.id = id;
		this.httpMethod = httpMethod;
		this.link = link;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getEndpointName() {
		return endpointName;
	}
	public void setEndpointName(String endpointName) {
		this.endpointName = endpointName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getHttpMethod() {
		return httpMethod;
	}
	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	
}
