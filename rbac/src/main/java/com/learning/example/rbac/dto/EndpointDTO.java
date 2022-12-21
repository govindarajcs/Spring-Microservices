package com.learning.example.rbac.dto;

public class EndpointDTO {
	
	Integer id;
	String endpointName;
	String httpMethod;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEndpointName() {
		return endpointName;
	}
	public void setEndpointName(String endpointName) {
		this.endpointName = endpointName;
	}
	public String getHttpMethod() {
		return httpMethod;
	}
	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}
	
}
